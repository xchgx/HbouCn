package com.xchgx.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xchgx.cons.PropertyFileOperation;
import com.xchgx.dao.ArticleDao;
import com.xchgx.dao.Page;
import com.xchgx.dao.SectionDao;
import com.xchgx.domain.Article;
import com.xchgx.domain.Section;
import com.xchgx.zsbwork.bean.Department;
import com.xchgx.zsbwork.dao.DepartmentDao;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;
@Service
public class FreeMarkertUtil {
	@Autowired
	private DepartmentDao departmentDao;
	@Autowired
	private SectionDao sectionDao;
	@Autowired
	private ArticleService articleService;
	@Autowired
	private ArticleDao articleDao;
	/**
	 *  生成静态文件。。。
	 * @param templatePath 模板路径
	 * @param templateName 模板名称.ftl
	 * @param fileName  生成后的文件名
	 * @param root  数据模型
	 */
	public void analysisTemplate(String templatePath,
			String templateName, String fileName, Map<?, ?> root) {
		try {
			Configuration config = new Configuration();
			// 设置要解析的模板所在的目录，并加载模板文件
			config.setDirectoryForTemplateLoading(new File(templatePath));
			// 设置包装器，并将对象包装为数据模型
			config.setObjectWrapper(new DefaultObjectWrapper());

			// 获取模板,并设置编码方式，这个编码必须要与页面中的编码格式一致
			// 否则会出现乱码
			Template template = config.getTemplate(templateName, "UTF-8");
			// 合并数据模型与模板
			FileOutputStream fos = new FileOutputStream(fileName);
			Writer out = new OutputStreamWriter(fos, "UTF-8");
			template.process(root, out);
			out.flush();
			out.close();
			fos.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (TemplateException e) {
			e.printStackTrace();
		}
	}
	/**
	 *  更新文章相关页面
	 * <p>更新文章首页和内容页</p>
	 * <p>更新部门相关页面
	 * <li>更新部门首页</li>
	 * <li>更新部门文章列表页</li>
	 * <li>更新部门webPath页</li>
	 * <li>更新部门栏目页</li>
	 * </p>
	 * <p>更新栏目首页和文章列表页</p>
	 * @param articleId
	 * @param delete
	 * @return
	 */
	public int htmlArticleJsp(Integer articleId, Integer departmentId, Integer sectionId, boolean delete){
		int i=0;
			System.out.println("准备"+delete+"文章首页和文章内容页");
			i+=htmlArticleIndexAndContentJsp(articleId, delete); //静态化文章首页和内容页
			i+=htmlArticleWebPathJsp(articleId, delete);//文章webPath路径页
			System.out.println("准备"+delete+"部门("+departmentId+")相关所有页");
			//		i+=htmlDepartment(article.getDepartment().getId(), delete);//静态化部门相关所有页
			//静态化部门相关所有页{

			i+=htmlDepartmentIndex(departmentId, false);
			System.out.println(""+delete+"部门文章列表页");
			i+=htmlDepartmentArticleListJsp(departmentId,false);
			System.out.println(""+delete+"部门栏目列表页");
			i+=htmlDepartmentSectionsJsp(departmentId,false);
			System.out.println(""+delete+"部门webPath列表页");
			i+=htmlDepartmentWebPathJsp(departmentId, false);
			i+=htmlDynPreviewArticleListJsp(departmentId, 10, departmentId==7?15:9);
			//静态化部门相关所有页}
			System.out.println("准备"+delete+"栏目相关页面");
			i+= htmlSectionIndexJsp(sectionId, departmentId, false);//创建更新栏目首页
			i+=htmlSectionArticleListJsp(sectionId);//创建更新栏目文章页
			i+=htmlSectionListJsp(sectionId, false);//左下角的备注简介
			i+=htmlSectionWebPathJsp(sectionId, false);//webPah页
		return i;
		
	}
	/**
	 * 生成文章页面，包括首页art${id}.jsp和文章页${id}.jsp
	 * @param article
	 * @param delete 是否删除
	 * @return 1
	 */
	public int htmlArticleIndexAndContentJsp(Integer articleId, boolean delete){
		PropertyFileOperation p = new PropertyFileOperation();
		String rootPath = p.getRootPath();
		String basePath = p.getBasePath();
		if(delete){
			// ---删除文章首页 start{
			String indexFile = rootPath + "/index/article/art"+articleId+".jsp";
			File f = new File(indexFile);
			if(f.exists()) f.delete();
			// ---- 删除文章首页 stop}
			
			// --- 删除文章页  start{
			indexFile = rootPath + "/index/article/"+articleId+".jsp";
			f = new File(indexFile);
			if(f.exists())f.delete();
			// ---- 删除文章页  stop}
			
			return 0;
		}else{
			//----静态化文章内容页 start{
			String indexFile = rootPath + "/index/article/art"+articleId+".jsp";
			String templatePath = rootPath + "/WEB-INF/ftl/article/";
			String templateName = "/index.jsp.ftl";
			Article article = articleDao.get(articleId);
			Map<String, Object> root = new HashMap<String, Object>();
			root.put("basePath", basePath);
			root.put("department", article.getDepartment());
			root.put("section", article.getSection());
			root.put("article", article);

			analysisTemplate(templatePath, templateName, indexFile,root);
			//}-----静态化文章内容页 stop

			//----静态化文章首页 start{
			System.out.println("releaseFreemarkerArticleIndex："+indexFile);
			indexFile = rootPath + "/index/article/"+article.getId()+".jsp";
			templateName = "/article.jsp.ftl";
			analysisTemplate(templatePath, templateName, indexFile, root);
			//}----静态化文章首页 stop
			htmlArticleWebPathJsp(articleId, delete); //静态化文章页的路径页
			return 2;
		}
	}
	/**
	 * 静态化文章webPath路径页面
	 * 
	 * @param departmentId
	 * @param delete
	 * @return
	 */
	public int htmlArticleWebPathJsp(Integer articleId, boolean delete){
		PropertyFileOperation p = new PropertyFileOperation();
		String rootPath = p.getRootPath();
		String basePath = p.getBasePath();
		String indexFile = rootPath + "/index/article/content_r1c2_art"+articleId+".jsp";
		if(delete){
			// ---删除文章WebPath start{
			File f = new File(indexFile);
			if(f.exists()) f.delete();
			// ---- 删除文章WebPath stop}
			return 0;
		}else{
			String templatePath = rootPath + "/WEB-INF/ftl/article/";
			String templateName = "/content_r1c2.jsp.ftl";

			List<String> webPath = getWebPathByArticle(articleId);
			Map<String, Object> root = new HashMap<String, Object>();
			root.put("basePath", basePath);
			root.put("wpath", webPath);

			analysisTemplate(templatePath, templateName, indexFile,root);
			System.out.println("releaseFreemarkerDepartmentWebPath："+indexFile);
		}
		return 1;
	}
	
	/**
	 *  静态化部门相关页面。
	 * 首页<br>
	 * 文章页<br>
	 * 栏目页<br>
	 * @param departmentId
	 * @param delete true删除 false创建
	 * @return
	 */
	public int htmlDepartment(Integer departmentId, boolean delete){
		int i=0;
		System.out.println(delete+"部门首页");
		i+=htmlDepartmentIndex(departmentId, delete);
		System.out.println(delete+"部门文章列表页");
		i+=htmlDepartmentArticleListJsp(departmentId,delete);
		System.out.println(delete+"部门栏目列表页");
		i+=htmlDepartmentSectionsJsp(departmentId,delete);
		System.out.println(delete+"部门webPath页");
		i+=htmlDepartmentWebPathJsp(departmentId, delete);
		return i;
	}
	/**
	 * 静态化部门列表content_r2c2_list_dpt${dptId}_${page}.jsp
	 * 只是静态化某一页，全部静态需要循环调用这个方法
	 * @param department
	 * @param page
	 * @return
	 */
	public int htmlDepartmentArticleListJsp(Integer departmentId, boolean delete){
		PropertyFileOperation p = new PropertyFileOperation();
		String rootPath = p.getRootPath();
		String basePath = p.getBasePath();
		String indexFile = rootPath + "/index/department/content_r2c2_list_dpt"+departmentId;
//		if(delete){
//			//通过java使用通配符来选中文件，然后删除。
//			
//			
//		}else{
			String templatePath = rootPath + "/WEB-INF/ftl/department/";
			String templateName = "/content_r2c2_list.jsp.ftl";

			int pgno=1;
			Department department = departmentDao.get(departmentId);
			Page page;
			Map<String, Object> root;
			do{
				page = articleService.getPageJsonDepartmentArticlesSimple(pgno, 30, departmentId);
				root = new HashMap<String, Object>();
				String file = indexFile + "_"+pgno+".jsp";
				root.put("basePath", basePath);
				root.put("departmentId", department.getId());
				root.put("page", page);
				root.put("currentPageNo", page.getCurrentPageNo());
				root.put("strLength", 20);
				analysisTemplate(templatePath, templateName, file, root);
				System.out.println("releaseFreemarkerDepartmentArticles："+file);
				pgno++;
			}while(page.getCurrentPageNo() < page.getTotalPageCount());
//		}
		return 1;
	}
	/**
	 * 静态化部门页左下角的栏目列表
	 * @param departmentId
	 * @return
	 */
	public int htmlDepartmentSectionsJsp(Integer departmentId,boolean delete){
		PropertyFileOperation p = new PropertyFileOperation();
		String rootPath = p.getRootPath();
		String basePath = p.getBasePath();
		String indexFile = rootPath + "/index/department/content_r2c1_dpt"+departmentId+".jsp";
		if(delete){
			File f = new File(indexFile);
			if(f.exists())f.delete();
		}else{
			String templatePath = rootPath + "/WEB-INF/ftl/department/";
			String templateName = "/content_r2c1.jsp.ftl";

			Department department = departmentDao.get(departmentId);

			Map<String, Object> root = new HashMap<String, Object>();
			root.put("basePath", basePath);
			root.put("sections", department.getSections());
			root.put("department", department);
			analysisTemplate(templatePath, templateName, indexFile,root);
			System.out.println("releaseFreemarkerDepartmentSections："+indexFile);
		}
		return 1;
	}
	/**
	 * 静态化部门首页
	 * @param departmentId
	 * @return
	 */
	public int htmlDepartmentIndex(Integer departmentId, boolean delete){
		PropertyFileOperation p = new PropertyFileOperation();
		String rootPath = p.getRootPath();
		String basePath = p.getBasePath();
		String indexFile = rootPath + "/index/department/dpt"+departmentId+".jsp";
		if(delete){
			File f = new File(indexFile);
			if(f.exists()) f.delete();
		}else{
			String templatePath = rootPath + "/WEB-INF/ftl/department/";
			String templateName = "/index.jsp.ftl";

			Department department = departmentDao.get(departmentId);
			Map<String, Object> root = new HashMap<String, Object>();
			root.put("basePath", basePath);
			root.put("department", department);
			analysisTemplate(templatePath, templateName, indexFile,root);
			System.out.println("releaseFreemarkerDepartmentIndex："+indexFile);
		}
		return 1;
	}

	/**
	 * 静态化部门的动态预览文章列表页面。
	 * @param departmentId
	 * @param size
	 * @param strLength
	 * @return
	 */
	public int htmlDynPreviewArticleListJsp(Integer departmentId,Integer size,Integer strLength){
		PropertyFileOperation p = new PropertyFileOperation();
		String rootPath = p.getRootPath();
		String basePath = p.getBasePath();
		
		String indexFile = rootPath + "/index/articleList/preview/previewDepartmentArticleList"+departmentId+".jsp";
		String templatePath = rootPath + "/WEB-INF/ftl/index/";
		String templateName = "/previewArticleList.jsp.ftl";
		
		Page page = articleService.getPageJsonDepartmentArticlesSimple(1, size, departmentId);
		Map<String, Object> root = new HashMap<String, Object>();
		root.put("basePath", basePath);
		root.put("strLength", strLength);
		root.put("articles", page.getResult());
		analysisTemplate(templatePath, templateName, indexFile,root);
		System.out.println("releaseFreemarkerDynPreviewArticleList"+indexFile);
		 
		return 1;
	}
	// 获得当前页面的web路径
	/**
	 * 获得当前部门的路径
	 * @param request
	 * @param departmentId
	 * @return
	 */
	public List<String> getWebPathByDepartment(Integer departmentId) {
		PropertyFileOperation p = new PropertyFileOperation();
		String basePath = p.getBasePath();
		
		Department department = departmentDao.get(departmentId);
		List<String> path = new ArrayList<String>();
		path.add("<a href='"+basePath+"'/>首页</a>");
		path.add("<a href='"+basePath+"/index/department/dpt"+department.getId()+".jsp'>"+department.getName()+"</a>");
		return path;
	}
	/**
	 * 获得当前栏目的路径
	 * @param request
	 * @param departmentId
	 * @return
	 */
	public List<String> getWebPathBySection(Integer sectionId) {
		PropertyFileOperation p = new PropertyFileOperation();
		String basePath = p.getBasePath();
		
		Section section = sectionDao.get(sectionId);
		// String path =
		// "首页>"+section.getDepartment().getName()+">"+section.getName();
		List<String> path = new ArrayList<String>();
		path.add("<a href='"+basePath+"'/>首页</a>");
		path.add("<a href='"+basePath+"/index/department/dpt"+section.getDepartment().getId()+".jsp'>"+section.getDepartment().getName()+"</a>");
		path.add("<a href='"+basePath+"/index/section/sec"+section.getId()+".jsp'>"+section.getName()+"</a>");
		return path;
	}
	/**
	 * 通过文章计算当前文章所在的web路径
	 * 
	 * @param articleId
	 * @return
	 */
	public @ResponseBody List<String> getWebPathByArticle(Integer articleId) {
		PropertyFileOperation p = new PropertyFileOperation();
		String basePath = p.getBasePath();
		
		Article article = articleService.getArticleById(articleId);
		List<String> path = new ArrayList<String>();
		path.add("<a href='"+basePath+"'/>首页</a>");
		path.add("<a href='"+basePath+"/index/department/dpt"+article.getDepartment().getId()+".jsp'>"+article.getDepartment().getName()+"</a>");
		path.add("<a href='"+basePath+"/index/section/sec"+article.getSection().getId()+".jsp'>"+article.getSection().getName()+"</a>");
		path.add("<a href='"+basePath+"/index/article/art"+article.getId()+".jsp'>"+article.getTitle()+"</a>");
		return path;
	}
	/**
	 * 静态化部门webPath路径页面
	 * 
	 * @param departmentId
	 * @param delete
	 * @return
	 */
	public int htmlDepartmentWebPathJsp(Integer departmentId, boolean delete){
		PropertyFileOperation p = new PropertyFileOperation();
		String rootPath = p.getRootPath();
		String basePath = p.getBasePath();

		String indexFile = rootPath + "/index/department/content_r1c2_dpt"+departmentId+".jsp";
		String templatePath = rootPath + "/WEB-INF/ftl/department/";
		String templateName = "/content_r1c2.jsp.ftl";
		
		List<String> webPath = getWebPathByDepartment(departmentId);
		Map<String, Object> root = new HashMap<String, Object>();
		root.put("basePath", basePath);
		root.put("wpath", webPath);
		root.put("departmentId", departmentId);
		analysisTemplate(templatePath, templateName, indexFile,root);
		System.out.println("releaseFreemarkerDepartmentWebPath："+indexFile);
		 
		return 1;
	}
	/**
	 *  静态化栏目相关页面
	 * <p>栏目首页</p>
	 * <p>栏目文章列表页</p>
	 * @param sectionId
	 * @return
	 */
	public int htmlSectionJsp(Integer sectionId,Integer departmentId, boolean delete){
		int i=0;
		i+= htmlSectionIndexJsp(sectionId, departmentId, delete);//创建更新栏目首页
		i+=htmlSectionArticleListJsp(sectionId);//创建更新栏目文章页
		i+=htmlSectionListJsp(sectionId, delete);//左下角的备注简介
		i+=htmlSectionWebPathJsp(sectionId, delete);//webPah页
		i+=htmlDepartment(departmentId, false);
		return i;
	}

	/**
	 * 静态化栏目首页
	 * @param sectionId
	 * @return
	 */
	public int htmlSectionIndexJsp(Integer sectionId, Integer departmentId, boolean delete){
		PropertyFileOperation p = new PropertyFileOperation();
		String rootPath = p.getRootPath();
		String basePath = p.getBasePath();

		String indexFile = rootPath + "/index/section/sec"+sectionId+".jsp";
		if(delete){
			File f = new File(indexFile);
			if(f.exists()) f.delete();
		}else{
			String templatePath = rootPath + "/WEB-INF/ftl/section/";
			String templateName = "/index.jsp.ftl";

			Department department = departmentDao.get(departmentId);
			Map<String, Object> root = new HashMap<String, Object>();
			root.put("basePath", basePath);
			root.put("department", department);
			root.put("sectionId", sectionId);

			analysisTemplate(templatePath, templateName, indexFile,root);
			System.out.println("releaseFreemarkerSectionIndex："+indexFile);
		}
		return 1;
	}
	
	/**
	 * 静态化栏目板块下的文章列表
	 * @param sectionId
	 * @return
	 */
	public int htmlSectionArticleListJsp(Integer sectionId){
		PropertyFileOperation p = new PropertyFileOperation();
		String rootPath = p.getRootPath();
		String basePath = p.getBasePath();
		
		System.out.println("releaseFreemarkerSectionArticles..."+sectionId);
		String indexFile = rootPath + "/index/section/sectionArticles"+sectionId;
		String templatePath = rootPath + "/WEB-INF/ftl/section/";
		String templateName = "/sectionArticles.jsp.ftl";

		int pgno=1;
		Page page;
		Map<String, Object> root;
		do{
			page = articleService.getPageJsonSectionArticlesSimple(pgno++, 30, sectionId);
			root = new HashMap<String, Object>();
			String file = indexFile + "_"+page.getCurrentPageNo()+".jsp";
			root.put("basePath", basePath);
			root.put("sectionId", sectionId);
			root.put("page", page);
			root.put("currentPageNo", pgno);
			root.put("strLength", 20);
			analysisTemplate(templatePath, templateName, file, root);
			System.out.println("releaseFreemarkerSectionArticles："+file);
		}while(page.getCurrentPageNo() < page.getTotalPageCount());
		 
		return 1;
	}
	/**
	 * 静态化栏目左下角备注简介页面
	 * @param sectionId
	 * @param delete 
	 * @return
	 */
	public int htmlSectionListJsp(Integer sectionId, boolean delete){
		PropertyFileOperation p = new PropertyFileOperation();
		String rootPath = p.getRootPath();
		String basePath = p.getBasePath();

		String indexFile = rootPath + "/index/section/content_r2c1_sec"+sectionId+".jsp";
		if(delete){
			File f = new File(indexFile);
			if(f.exists()) f.delete();
		}else{
			String templatePath = rootPath + "/WEB-INF/ftl/section/";
			String templateName = "/content_r2c1.jsp.ftl";

			Section section = sectionDao.get(sectionId);
			Map<String, Object> root = new HashMap<String, Object>();
			root.put("basePath", basePath);
			root.put("department", section.getDepartment());
			root.put("section", section);

			analysisTemplate(templatePath, templateName, indexFile,root);
			System.out.println("releaseFreemarkerSectionIndex："+indexFile);
		}
		return 1;
	}
	/**
	 * 静态化栏目webPath路径页面
	 * 
	 * @param departmentId
	 * @param delete
	 * @return
	 */
	public int htmlSectionWebPathJsp(Integer sectionId, boolean delete){
		PropertyFileOperation p = new PropertyFileOperation();
		String rootPath = p.getRootPath();
		String basePath = p.getBasePath();

		String indexFile = rootPath + "/index/section/content_r1c2_sec"+sectionId+".jsp";
		if(delete){
			File f = new File(indexFile);
			if(f.exists()) f.delete();
		}else{
			String templatePath = rootPath + "/WEB-INF/ftl/section/";
			String templateName = "/content_r1c2.jsp.ftl";

			List<String> webPath = getWebPathBySection(sectionId);
			Map<String, Object> root = new HashMap<String, Object>();
			root.put("basePath", basePath);
			root.put("wpath", webPath);

			analysisTemplate(templatePath, templateName, indexFile,root);
			System.out.println("releaseFreemarkerDepartmentWebPath："+indexFile);
		}
		return 1;
	}
}