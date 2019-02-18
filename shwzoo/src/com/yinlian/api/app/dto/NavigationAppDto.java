package com.yinlian.api.app.dto;

public class NavigationAppDto {
	    private Integer id;

	    private String title;

	    private Integer sort;

	    private String img;

	    private String url;

		public Integer getId() {
			return id;
		}

		public void setId(Integer id) {
			this.id = id;
		}

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		public Integer getSort() {
			return sort;
		}

		public void setSort(Integer sort) {
			this.sort = sort;
		}

		public String getImg() {
			return img;
		}

		public void setImg(String img) {
			this.img = img;
		}

		public String getUrl() {
			return url;
		}

		public void setUrl(String url) {
			this.url = url;
		}
}
