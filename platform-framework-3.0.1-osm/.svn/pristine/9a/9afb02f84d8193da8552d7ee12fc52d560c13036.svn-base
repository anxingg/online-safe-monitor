package cn.com.qytx.platform.test;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.qytx.platform.base.action.BaseControllerSupport;
import cn.com.qytx.platform.base.query.Page;
import cn.com.qytx.platform.org.domain.UserInfo;
import cn.com.qytx.platform.springmvc.SpringMvcPageable;

/**
 * 测试StrongBaseDao
 * @author jiayq
 *
 */
@Controller
@RequestMapping("/testStrongBaseDao")
public class TestStrongBaseDaoController extends BaseControllerSupport {

	@Resource
	private cn.com.qytx.platform.base.dao.TestStrongBaseDao testDao;
	
	/**
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/case1.c")
	public String case1(){
		List<UserInfo> list = testDao.case1();
		return SUCCESS(list.size());
	}
	
	@ResponseBody
	@RequestMapping("/case2.c")
	public String case2(){
		List<UserInfo> list = testDao.case2("张");
		return SUCCESS(list.size());
	}
	
	@ResponseBody
	@RequestMapping("/case3.c")
	public String case3(@ModelAttribute(value="page")SpringMvcPageable page ){
		Page<UserInfo> pa = testDao.case3("张",page.getPageable());
		return SUCCESS_PAGE(pa);
	}
	
	@ResponseBody
	@RequestMapping("/case4.c")
	public String case4(){
		List<Object[]> os = testDao.case4();
		return SUCCESS(os.size());
	}
	
	@ResponseBody
	@RequestMapping("/case5.c")
	public String case5(){
		List<Map<String,Object>> list = testDao.case5(273244);
		for(int i=0; i<list.size(); i++){
			Map<String,Object> map = list.get(i);
			System.out.println(map.get("userId"));
			System.out.println(map.get("groupName"));
		}
		return SUCCESS(list.size());
	}
	
	@ResponseBody
	@RequestMapping("/case6.c")
	public String case6(@ModelAttribute("page")SpringMvcPageable page){
		Page<UserInfo> ulist = testDao.case6(273244, page.getPageable());
		for(int i=0; i<ulist.getContent().size(); i++){
			UserInfo ui = ulist.getContent().get(i);
			System.out.println(ui.getUserId());
			System.out.println(ui.getUserName());
		}
		return SUCCESS(ulist.getContent().size());
	}
}
