package org.secondopinion.superadmin.serviceImpl;

import java.io.IOException;
import java.util.Objects;

import org.apache.commons.lang3.StringUtils;
import org.secondopinion.superadmin.dao.BlogDAO;
import org.secondopinion.superadmin.dto.Blog;
import org.secondopinion.superadmin.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
public class BlogServiceImpl implements BlogService {

	@Autowired
	private BlogDAO blogDAO;

	@Override
	@Transactional
	public void saveBlog(Blog blog) {
		if (blog.getBlogId() != null) {
			Blog dbblog = getBlogByBlogId(blog.getBlogId());
			if (Objects.nonNull(dbblog)) {
				dbblog.setTitle(StringUtils.isEmpty(blog.getTitle()) ? dbblog.getTitle() : (blog.getTitle()));
				dbblog.setDescription(
						StringUtils.isEmpty(blog.getDescription()) ? dbblog.getDescription() : (blog.getDescription()));
				blogDAO.save(dbblog);

			}
		}

		blogDAO.save(blog);

	}

	@Override
	@Transactional
	public Blog getBlogByBlogId(Long blogId) {
		Blog blog = blogDAO.findById(blogId);
		if (Objects.isNull(blog)) {
			return new Blog();
		}
		byte[] image = blog.getImage();
		blog.setImage(image);
		return blog;
	}

	@Override
	@Transactional
	public void saveBlogWithImage(Blog blogs, MultipartFile blogPic) {
		if (blogs.getBlogId() != null) {
			Blog dbblog = getBlogByBlogId(blogs.getBlogId());
			if (Objects.nonNull(dbblog)) {
				dbblog.setTitle(StringUtils.isEmpty(blogs.getTitle()) ? dbblog.getTitle() : (blogs.getTitle()));
				dbblog.setDescription(StringUtils.isEmpty(blogs.getDescription()) ? dbblog.getDescription()
						: (blogs.getDescription()));
			}
		}

		try {
			byte[] image = blogPic.getBytes();
			blogs.setImage(image);
		} catch (IOException e) {
			e.printStackTrace();
		}
		blogDAO.save(blogs);

	}

}
