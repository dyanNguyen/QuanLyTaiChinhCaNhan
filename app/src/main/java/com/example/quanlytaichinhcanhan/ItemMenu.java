package com.example.quanlytaichinhcanhan;

public class ItemMenu {
    public String ItemName;
    public int icon;
    //public boolean isSelected; // thuộc tính để lưu trữ trạng thái của item
    public ItemMenu(String ItemName,int icon) {
        this.ItemName = ItemName;
        this.icon = icon;
        //this.isSelected = false; // ban đầu item chưa được chọn
    }
}
