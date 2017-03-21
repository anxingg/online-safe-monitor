<task name="${name}" candidate-users="${candidate}" >
        <assignment-handler class="cn.com.qytx.workflow.jpdl.template.TaskCreateHandler">
        </assignment-handler>
		<#list paths as path>
      		<transition name="${path.text.text}" to="${path.to}" />
      	</#list>
 </task>