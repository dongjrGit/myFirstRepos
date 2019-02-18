package com.yinlian.wssc.web.po;

public class GroupbuyhistoryWithBLOBs extends Groupbuyhistory {
	 private String content;

	    private String buynotes;

	    public String getContent() {
	    	  if (content != null && content.indexOf("\"") > 0) {
	              this.content = content.replace("\"", "'");
	          }
	        return content;
	    }

	    public void setContent(String content) {
	        this.content = content == null ? null : content.trim();
	    }

	    public String getBuynotes() {
	    	if (buynotes != null && buynotes.indexOf("\"") > 0) {
	            this.buynotes = buynotes.replace("\"", "'");
	        }
	        return buynotes;
	    }

	    public void setBuynotes(String buynotes) {
	        this.buynotes = buynotes == null ? null : buynotes.trim();
	    }
}