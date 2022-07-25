package org.secondopinion.superadmin.controller;

import org.secondopinion.request.Response;
import org.secondopinion.request.Response.StatusEnum;
import org.secondopinion.superadmin.dto.Blog;

import org.secondopinion.superadmin.exception.SuperAdminServerException;
import org.secondopinion.superadmin.service.BlogService;
import org.secondopinion.utils.JSONHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("/")
@Api("/")
public class BlogController {

	@Autowired
	private BlogService blogService;

	@PostMapping(value = "/saveBlog")
	public ResponseEntity<Response<String>> saveBlog(@RequestBody Blog blog) {

		try {
			blogService.saveBlog(blog);
			return new ResponseEntity<>(new Response<>("Blog data saved.", StatusEnum.SUCCESS, "Blog data saved."),
					HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new SuperAdminServerException(e.getMessage(), e);
		}

	}

	@PostMapping(path = "/saveblogWithImage", consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_OCTET_STREAM_VALUE })
	public ResponseEntity<Response<String>> saveBlogWithImage(

			@RequestParam("blog") String blog,
			@RequestParam(name = "blogPic", required = false) MultipartFile blogPic) {

		try {
			Gson gson = JSONHelper.getGsonWithDate();
			Blog blogs = gson.fromJson(blog, Blog.class);
			blogService.saveBlogWithImage(blogs, blogPic);
			return new ResponseEntity<>(new Response<>("Blog data saved.", StatusEnum.SUCCESS, "Blog data saved."),
					HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new SuperAdminServerException(e.getMessage(), e);
		}

	}

	@GetMapping(value = "/getBlogByBlogId")
	public ResponseEntity<Response<Blog>> getBlogByBlogId(@RequestParam Long blogId) {

		try {
			Blog blog = blogService.getBlogByBlogId(blogId);
			return new ResponseEntity<>(new Response<>(blog, StatusEnum.SUCCESS, "Blog data get."), HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new SuperAdminServerException(e.getMessage(), e);
		}

	}
}
