package cn.com.qytx.hotline.util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.Field.Index;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryParser.MultiFieldQueryParser;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.ParallelMultiSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.highlight.Formatter;
import org.apache.lucene.search.highlight.Fragmenter;
import org.apache.lucene.search.highlight.Highlighter;
import org.apache.lucene.search.highlight.InvalidTokenOffsetsException;
import org.apache.lucene.search.highlight.QueryScorer;
import org.apache.lucene.search.highlight.Scorer;
import org.apache.lucene.search.highlight.SimpleFragmenter;
import org.apache.lucene.search.highlight.SimpleHTMLFormatter;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.wltea.analyzer.lucene.IKAnalyzer;

import cn.com.qytx.hotline.knowledge.domain.Knowledge;
import cn.com.qytx.monitor.client.log.MonitorLogger;
import cn.com.qytx.monitor.client.logImpl.Log4jImpl;
import cn.com.qytx.platform.base.query.Page;
import cn.com.qytx.platform.base.query.PageImpl;
import cn.com.qytx.platform.base.query.Pageable;

/**
 * 功能: 版本: 1.0 开发人员: 彭小东 创建日期: 2014-12-12 修改日期: 2014-12-12 修改列表:
 */
@SuppressWarnings("deprecation")
public class LuceneUtil {
	/**
	 * log4j日志对象
	 */
    private final static MonitorLogger logluce =new Log4jImpl(LuceneUtil.class);
	// 标准分词器
	private static Analyzer luceneAnalyzer;
	// 根索引存放目录
	private static String lunceneRoot = PropertiesUtil.get("lunceneRoot",
			"E://lucene//index//");
	private static String fileName;
	private final static String _knowid = "knowid";
	private final static String _end = "_end";
	private final static String _title = "title";
	private final static String _contentInfo = "contentInfo";
	static {
		/*
		 * InputStream in = PropertiesUtil.class
		 * .getResourceAsStream("/voxFilePath.properties"); //建立一个标准分词器，版本为3.6
		 * luceneAnalyzer = new StandardAnalyzer(Version.LUCENE_36);
		 */
		// 中文分词器
		luceneAnalyzer = new IKAnalyzer();

	}

	/**
	 * 功能：根据知识获取路径
	 * 
	 * @param index
	 */
	private static String getPathByKnow(Knowledge kd) {
		String fileName = "";
		if (kd.getIsForkGroup() == null) {
			return null;
		}
		if (kd.getIsPrivate() > 0) {
			fileName = "myKnowledge//file" + kd.getCreateUserInfo().getUserId();
		} else if (kd.getIsForkGroup() > 0) {
			fileName = "knowledge" + kd.getIsForkGroup();
		} else {
			fileName = null;
		}
		return fileName;
	}

	/**
	 * 功能：根据知识获取目录
	 * 
	 * @param index
	 */
	private static Directory getDirectory(Knowledge kd) {
		String fileName = getPathByKnow(kd);
		if (fileName == null || "".equals(fileName)) {
			return null;
		} else {
			Directory directory = getDirectoryByPath(fileName);
			return directory;
		}

	}

	/**
	 * 功能：根据文件名获取目录
	 * 
	 * @param index
	 */
	private static Directory getDirectoryByPath(String fileName) {
		Directory directory = null;
		// 创建索引目录
		try {
			File index = getFileByPath(lunceneRoot + fileName);
			directory = FSDirectory.open(index);

		} catch (IOException e) {
			logluce.error(lunceneRoot + fileName + "打根目录错误" + e.getMessage());
		}
		return directory;
	}

	// 创建索引
	public static void createIndex(List<Knowledge> kdList) {
		// 建立一个indexWriter配置，指定版本以及分词器

		String fileName = "";
		Document doc = null;
		for (Knowledge kd : kdList) {
			IndexWriterConfig indexWConfig = new IndexWriterConfig(
					Version.LUCENE_36, luceneAnalyzer);
			IndexWriter indexWriter = null;
			fileName = getPathByKnow(kd);
			if (fileName == null || "".equals(fileName)) {
				continue;
			}
			Directory directory = getDirectoryByPath(fileName);
			try {
				// 创建一个indexWriter，它负责索引的创建和维护
				indexWriter = new IndexWriter(directory, indexWConfig);
				doc = getDocumentByKd(kd);
				// 把Document加入到索引中
				indexWriter.addDocument(doc);
				indexWriter.optimize();

			} catch (Exception e) {
				indexUnlock(indexWriter, directory);
				logluce.error(kd.getTitle() + "生成索引失败" + e.getMessage());
				// e.printStackTrace();
			} finally {

				indexClose(indexWriter);
			}
		}

	}

	/**
	 * 功能：
	 * 
	 * @param kd
	 * @return
	 */
	private static Document getDocumentByKd(Knowledge kd) {
		// 建立document
		Document doc = new Document();
		// Store指定Field是否需要存储,Index指定Field是否需要分词索引

		doc.add(new Field(_knowid, kd.getVid().toString() + _end, Store.YES,
				Index.ANALYZED));
		// doc.add(new Field("knowledgeType",kd.getKnowledgeType().getName(),
		// Store.YES, Index.ANALYZED));
		if (kd.getTitle() != null) {
			doc.add(new Field(_title, kd.getTitle(), Store.YES, Index.ANALYZED));
		}
		if (kd.getContentInfo() != null) {

			doc.add(new Field(_contentInfo, kd.getContentInfo(), Store.YES,
					Index.ANALYZED));
		}
		if (kd.getKeyword() != null && kd.getKeyword() != "") {
			doc.add(new Field("keyword", kd.getKeyword(), Store.YES,
					Index.ANALYZED));
		}
		doc.add(new Field("createUserInfo", kd.getCreateUserInfo()
				.getUserName(), Store.YES, Index.NOT_ANALYZED));
		doc.add(new Field("createTimeStr", kd.getCreateTimeStr(), Store.YES,
				Index.NOT_ANALYZED));
		return doc;
	}

	// 创建索引
	public static void createIndex(Knowledge kd) {

		IndexWriter indexWriter = null;
		Directory directory = getDirectory(kd);
		if (directory != null) {
			try {
				// 建立一个indexWriter配置，指定版本以及分词器
				IndexWriterConfig indexWConfig = new IndexWriterConfig(
						Version.LUCENE_36, luceneAnalyzer);
				// 创建一个indexWriter，它负责索引的创建和维护
				indexWriter = new IndexWriter(directory, indexWConfig);
				// 建立document
				Document doc = getDocumentByKd(kd);
				// 把Document加入到索引中
				indexWriter.addDocument(doc);
				indexWriter.optimize();
			} catch (Exception e) {
				indexUnlock(indexWriter, directory);
				logluce.error(kd.getTitle() + "创建索引失败" + e.getMessage());
				// e.printStackTrace();
			} finally {

				indexClose(indexWriter);
			}

		}

	}

	/**
	 * 功能：解锁目录
	 * 
	 * @param indexWriter
	 * @param directory
	 */
	@SuppressWarnings("static-access")
	private static void indexUnlock(IndexWriter indexWriter, Directory directory) {
		try {
			indexWriter.unlock(directory);
		} catch (IOException e) {
			logluce.info("处理知识库解锁目录异常",e);
		}
	}

	/**
	 * 功能：解锁目录
	 * 
	 * @param indexWriter
	 * @param directory
	 */
	/*
	 * private static void isUnlock(IndexWriter indexWriter,Directory
	 * directory){ try { if(IndexWriter.isLocked(directory))
	 * IndexWriter.unlock(directory); } catch (IOException e) { 
	 * Auto-generated catch block e.printStackTrace(); } }
	 */
	/**
	 * 功能：indexWriter关闭
	 * 
	 * @param indexWriter
	 */
	private static void indexClose(IndexWriter indexWriter) {
		try {
			indexWriter.close();
		} catch (IOException e) {
		    logluce.info("处理知识库indexWriter关闭异常",e);
		}
	}

	// 搜索
	@SuppressWarnings("static-access")
	public static Page<Knowledge> searchBook(List<String> fileName,
			String keyWord, Pageable page) {
		List<Knowledge> kdList = new ArrayList<Knowledge>();
		int total = 0;
		// 创建多个搜索fields，这里是标题与内容 "createUserInfo" "knowledgeType"
		String[] fields = { _title, _contentInfo, "keyword" };
		// (在多个Filed中搜索)
		QueryParser queryParser = new MultiFieldQueryParser(Version.LUCENE_36,
				fields, luceneAnalyzer);
		// 设置查询条件是
		queryParser.setDefaultOperator(QueryParser.OR_OPERATOR);
		List<Directory> directory = getDirectoryList(fileName);
		if (directory == null || directory.size() < 1) {
			return null;
		}
		IndexSearcher[] indexSearchers = null;
		// 获取访问索引的接口,进行搜索
		IndexReader indexReader = null;
		ParallelMultiSearcher parallesearcher = null;
		try {
			// 通过查询分析器解析要搜索的关键字
			keyWord=queryParser.escape(keyWord);
			Query query = queryParser.parse(keyWord);
			indexSearchers = new IndexSearcher[directory.size()];
			for (int i = 0; i < directory.size(); i++) {
				indexReader = IndexReader.open(directory.get(i));
				indexSearchers[i] = new IndexSearcher(indexReader);
			}
			parallesearcher = new ParallelMultiSearcher(indexSearchers);
			TopDocs topDocs = parallesearcher.search(query, 50);
			/*
			 * int totalCount = topDocs.totalHits; // 搜索结果总数量
			 * logluce.info(fileName+"搜索到的结果总数量为："+ totalCount);
			 */
			// 搜索的结果列表
			ScoreDoc[] scoreDocs = topDocs.scoreDocs;
			total = scoreDocs.length;// 搜索结果总数量
			Highlighter highlighter = getHighlighterFormat(query);
			// 查询起始记录位置
			int begin = page.getPageSize() * page.getPageNumber();
			// 查询终止记录位置
			int end = Math.min(begin + page.getPageSize(), scoreDocs.length);
			// 把搜索结果取出放入到集合中
			// 进行分页查询
			Document doc = null;
			Knowledge kl = null;
			for (int i = begin; i < end; i++) {
				int docID = scoreDocs[i].doc;
				doc = parallesearcher.doc(docID);
				kl = getKnowledgeByDocu(highlighter, doc);
				kdList.add(kl);
			}
		} catch (Exception e) {
			logluce.error("查询失败" + e.getMessage());
		} finally {
			// 关闭
			for (int i = 0; i < indexSearchers.length; i++) {
				try {
					indexSearchers[i].close();
				} catch (IOException e) {
					logluce.error("关闭查询器失败" + e);
				}
			}
			try {
				indexReader.close();
				parallesearcher.close();
				logluce.info("关闭查询器");
			} catch (IOException e) {
				logluce.error("关闭查询器失败" + e);
			}
//			try {
//				parallesearcher.close();
//				logluce.info("关闭查询器");
//			} catch (IOException e) {
//				logluce.error("关闭查询器失败" + e);
//			}
		}
		formateKnowledge(kdList);
		Page<Knowledge> page2 = new PageImpl<Knowledge>(kdList, page, total);
		return page2;
	}

	/**
	 * 功能：
	 * 
	 * @param query
	 * @return
	 */
	private static Highlighter getHighlighterFormat(Query query) {
		// 创建高亮器,使搜索的关键词突出显示
		Formatter formatter = new SimpleHTMLFormatter("<em>", "</em>");
		Scorer fragmentScore = new QueryScorer(query);
		Highlighter highlighter = new Highlighter(formatter, fragmentScore);
		Fragmenter fragmenter = new SimpleFragmenter(200);
		highlighter.setTextFragmenter(fragmenter);
		highlighter.setMaxDocCharsToAnalyze(200);
		return highlighter;
	}

	// 根据查询结果获取知识库
	private static Knowledge getKnowledgeByDocu(Highlighter highlighter,
			Document doc) throws IOException, InvalidTokenOffsetsException {
		Knowledge kl;
		kl = new Knowledge();
		kl.setVid(Integer.parseInt(doc.get(_knowid).split("_")[0]));
		// 高亮显示title
		String title = doc.get(_title);
		String highlightertitle = highlighter.getBestFragment(luceneAnalyzer,
				_title, title);
		// 如果content中没有找到关键词
		if (highlightertitle == null) {
			highlightertitle = title;
		}
		kl.setTitle(highlightertitle);
		kl.setCreateUser(doc.get("createUserInfo"));
		kl.setCreateTimeStr(doc.get("createTimeStr"));
		// 高亮显示content
		String content = doc.get(_contentInfo);
		content = delHTMLTag(content);
		String highlighterContent = highlighter.getBestFragment(luceneAnalyzer,
				_contentInfo, content);
		// 如果content中没有找到关键词
		if (highlighterContent == null) {
			highlighterContent = content;
			if (highlighterContent.length() > 200) {
				highlighterContent = highlighterContent.substring(0, 200);
			}
		}
		kl.setContentInfo(highlighterContent);
		return kl;
	}

	/**
	 * 把知识库格式化
	 * 
	 * @param kdList
	 */
	private static void formateKnowledge(List<Knowledge> kdList) {
		if (kdList != null) {
			for (Knowledge k : kdList) {
				// 标题
				String title = StringUtils.defaultString(k.getTitle(), "");
				// 内容
				String contentInfo = StringUtils.defaultString(
						k.getContentInfo(), "");
				// 录入人
				String userName = StringUtils.defaultString(k.getCreateUser(),
						"");
				// 录入时间
				String createTimeStr = k.getCreateTimeStr();
				String htmlStr = "";
				htmlStr += "<div class='dailyDetailBox'>";
				// 更具关键字标红字体
				htmlStr += "<div class='big_title'><a href='javascript:toDetail("
						+ k.getVid() + ");' >" + title + "</a></div>";
				htmlStr += "<div class='dailyDetail'>";
				htmlStr += "<p>" + contentInfo + "</p>";
				htmlStr += "<p class='dailyTime'><span>录入人：" + userName
						+ "</span><span class='time'>录入时间：" + createTimeStr
						+ "</span></p>";
				htmlStr += "</div>";

				htmlStr += "</div>";
				k.setKdDetail(htmlStr);
			}
		}
	}

	// 删除索引
	public static void delete(Knowledge kd) {
		IndexWriter indexWriter = null;
		Directory dir = getDirectory(kd);
		try {
			if (dir != null) {
				// 建立一个indexWriter配置，指定版本以及分词器
				IndexWriterConfig indexWConfig = new IndexWriterConfig(
						Version.LUCENE_36, luceneAnalyzer);
				// 创建一个indexWriter，它负责索引的创建和维护
				indexWriter = new IndexWriter(dir, indexWConfig);
				indexWriter.deleteDocuments(new Term(_knowid, kd.getVid()
						.toString() + _end));
				indexWriter.optimize();

			}
		} catch (Exception e) {
			indexUnlock(indexWriter, dir);
			logluce.error(kd.getTitle() + "删除索引失败" + e.getMessage());
		} finally {
			try {
				indexWriter.close();
			} catch (Exception e) {
				logluce.error(e.getMessage());
			}
		}

	}

	// 获取查询的索引目录
	public static List<Directory> getDirectoryList(List<String> pathName) {
		List<Directory> directory = new ArrayList<Directory>();
		for (int i = 0; i < pathName.size(); i++) {
			File indfilePath = getFileByPath(lunceneRoot + pathName.get(i));
			// 存放索引的目录
			if (indfilePath.list().length > 2) {// 查询该文件下是否 已经创建过索引
				directory.add(getDirectoryByPath(pathName.get(i)));
			}
		}
		return directory;
	}

	/**
	 * 功能： 根据路径获取文件
	 * 
	 * @param path
	 * @return
	 */
	private static File getFileByPath(String path) {
		File indfilePath = new File(path);
		if (!indfilePath.exists()) {// 可能查询的文件不存在
			boolean flag = indfilePath.mkdirs();
			if (flag) {
				logluce.info(path + "创建成功");
			}
		}
		return indfilePath;
	}

	/**
	 * 功能：更新某个知识的索引
	 * 
	 * @param oldKnowledge
	 */
	public static void updateIndex(Knowledge oldKnowledge) {
		delete(oldKnowledge);
		createIndex(oldKnowledge);
	}

	/**
	 * 功能：
	 * 
	 * @param isFork
	 * @param type
	 *            0 所有的 1私人的 2 收藏的 3 公共的
	 * @param cuId
	 */
	public static void deleteAllIndex(Integer type, Integer isFork, Integer cuId) {
		String fileName = "";
		if (type == 1) {
			if (cuId != null && cuId > 0) {
				fileName = "myKnowledge//file" + cuId;
			} else {
				fileName = "myKnowledge";
			}
		} else if (type == 2) {
			if (cuId != null && cuId > 0) {
				fileName = "collectKnowledge//file" + cuId;
			} else {
				fileName = "collectKnowledge";
			}
		} else if (type == 3 && isFork != null && isFork > 0) {
			fileName = "knowledge" + isFork;
		}
		File file = new File(lunceneRoot + fileName.trim());
		deleteDir(file);
		logluce.info(lunceneRoot + fileName + "删除索引成功");

	}

	/**
	 * 删除空目录
	 * 
	 * @param dir
	 *            将要删除的目录路径
	 */
	@SuppressWarnings("unused")
	private static void doDeleteEmptyDir(String dir) {
		boolean success = (new File(dir)).delete();
		if (success) {
			logluce.info(dir + "删除索引成功");
		} else {
			logluce.error(dir + "删除索引失败");
		}
	}

	/**
	 * 递归删除目录下的所有文件及子目录下所有文件
	 * 
	 * @param dir
	 *            将要删除的文件目录
	 * @return boolean Returns "true" if all deletions were successful. If a
	 *         deletion fails, the method stops attempting to delete and returns
	 *         "false".
	 */
	private static boolean deleteDir(File dir) {
		if (dir.isDirectory()) {
			String[] children = dir.list();
			// 递归删除目录中的子目录下
			for (int i = 0; i < children.length; i++) {
				boolean success = deleteDir(new File(dir, children[i]));
				if (!success) {
					return false;
				}
			}
		}
		// 目录此时为空，可以删除
		return dir.delete();
	}

	// 删除收藏的索引
	public static void deleteCollect(Knowledge kd, Integer seatId) {
		IndexWriter indexWriter = null;
		String fileName = "collectKnowledge//file"
				+ kd.getCreateUserInfo().getUserId();
		Directory dir = getDirectoryByPath(fileName);
		try {

			if (dir != null) {
				// 建立一个indexWriter配置，指定版本以及分词器
				IndexWriterConfig indexWConfig = new IndexWriterConfig(
						Version.LUCENE_36, luceneAnalyzer);
				// 创建一个indexWriter，它负责索引的创建和维护
				indexWriter = new IndexWriter(dir, indexWConfig);
				indexWriter.deleteDocuments(new Term(_knowid, kd.getVid()
						.toString() + _end));
				indexWriter.optimize();

			}
		} catch (Exception e) {
			indexUnlock(indexWriter, dir);
			logluce.error(kd.getTitle() + "删除索引失败" + e.getMessage());
		} finally {
			try {
				indexWriter.close();
			} catch (Exception e) {
				logluce.error(e.getMessage());
			}
		}

	}

	/**
	 * 功能：添加收藏的索引
	 * 
	 * @param kc
	 */
	public static void addCollectIndex(Knowledge kd, Integer seatId) {
		fileName = "collectKnowledge//file" + seatId;
		Directory directory = getDirectoryByPath(fileName);
		IndexWriter indexWriter = null;
		if (directory != null) {
			try {
				// 建立一个indexWriter配置，指定版本以及分词器
				IndexWriterConfig indexWConfig = new IndexWriterConfig(
						Version.LUCENE_36, luceneAnalyzer);
				synchronized (luceneAnalyzer) {
					// 创建一个indexWriter，它负责索引的创建和维护
					indexWriter = new IndexWriter(directory, indexWConfig);

					indexWriter.setMaxBufferedDocs(100);
					// 建立document
					Document doc = new Document();
					// Store指定Field是否需要存储,Index指定Field是否需要分词索引
					doc.add(new Field(_knowid, kd.getVid().toString() + _end,
							Store.YES, Index.ANALYZED));
					// doc.add(new
					// Field("knowledgeType",kd.getKnowledgeType().getName(),
					// Store.YES, Index.ANALYZED));
					if (kd.getTitle() != null) {
						doc.add(new Field(_title, kd.getTitle(), Store.YES,
								Index.ANALYZED));
					}
					if (kd.getContentInfo() != null) {

						doc.add(new Field(_contentInfo, kd.getContentInfo(),
								Store.YES, Index.ANALYZED));
					}
					if (kd.getKeyword() != null && kd.getKeyword() != "") {
						doc.add(new Field("keyword", kd.getKeyword(),
								Store.YES, Index.ANALYZED));
					}
					doc.add(new Field("createUserInfo", kd.getCreateUserInfo()
							.getUserName(), Store.YES, Index.NOT_ANALYZED));
					doc.add(new Field("createTimeStr", kd.getCreateTimeStr(),
							Store.YES, Index.NOT_ANALYZED));
					// 把Document加入到索引中
					indexWriter.addDocument(doc);
					indexWriter.optimize();
				}
			} catch (Exception e) {
				indexUnlock(indexWriter, directory);
				logluce.error(kd.getTitle() + "创建索引失败" + e.getMessage());
			} finally {

				indexClose(indexWriter);
			}
		}

	}

	/**
	 * 功能：目录相同的创建索引
	 * 
	 * @param content
	 */
	public static void createIndexByFork(List<Knowledge> kdList) {
		// 建立一个indexWriter配置，指定版本以及分词器

		String fileName = "";
		Document doc = null;
		IndexWriterConfig indexWConfig = new IndexWriterConfig(
				Version.LUCENE_36, luceneAnalyzer);
		IndexWriter indexWriter = null;
		fileName = getPathByKnow(kdList.get(0));
		Directory directory = getDirectoryByPath(fileName);
		// 创建一个indexWriter，它负责索引的创建和维护
		try {
			indexWriter = new IndexWriter(directory, indexWConfig);
			for (Knowledge kd : kdList) {
				doc = getDocumentByKd(kd);
				// 把Document加入到索引中
				indexWriter.addDocument(doc);
			}
			indexWriter.optimize();
		} catch (Exception e) {
			indexUnlock(indexWriter, directory);
			logluce.error("生成索引失败" + e.getMessage());
		} finally {
			indexClose(indexWriter);
		}

	}

	/**
	 * 功能：处理html代码
	 * 
	 * @param htmlStr
	 * @return
	 */
	public static String delHTMLTag(String htmlStr) {
		String regEx_script = "<script[^>]*?>[\\s\\S]*?<\\/script>";
		String regEx_style = "<style[^>]*?>[\\s\\S]*?<\\/style>";
		String regEx_html = "<[^>]+>";

		Pattern p_script = Pattern.compile(regEx_script,
				Pattern.CASE_INSENSITIVE);
		Matcher m_script = p_script.matcher(htmlStr);
		htmlStr = m_script.replaceAll("");

		Pattern p_style = Pattern
				.compile(regEx_style, Pattern.CASE_INSENSITIVE);
		Matcher m_style = p_style.matcher(htmlStr);
		htmlStr = m_style.replaceAll("");

		Pattern p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
		Matcher m_html = p_html.matcher(htmlStr);
		htmlStr = m_html.replaceAll("");

		return htmlStr.trim();
	}
}
