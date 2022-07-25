package org.secondopinion.superadmin.service;

import org.secondopinion.superadmin.dto.Blog;
import org.springframework.web.multipart.MultipartFile;

public interface BlogService {

  void saveBlog(Blog blog);
  
  Blog getBlogByBlogId(Long blogId);

void saveBlogWithImage(Blog blogs, MultipartFile blogPic);
}
