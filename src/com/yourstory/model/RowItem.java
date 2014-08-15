package com.yourstory.model;


//import com.andalias.wheresmymoolah.R;

public class RowItem {
	
	private String imageId;
    private String title;
    
    public RowItem(String title, String imageId) {
        this.imageId = imageId;
        this.title = title;
        
    }
    public String getImageId() {
        return imageId;
    }
    public void setImageId(String imageId) {
        this.imageId = imageId;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    @Override
    public String toString() {
        return title + "\n";
    }
}
