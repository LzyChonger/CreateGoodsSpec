package com.lzy.hello;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lzy.
 */

public class StoreManagerListEntity implements Serializable {

    public static class SkuListEntity implements Serializable {
        /*
        "sku_id": "7",
		"spec": "绿色:10寸",
		"sku_name": "颜色,尺码",
		"price": "10.00",
		"stock": "0"
         */
        public String sku_id;
        public String spec;
        public String sku_name;
        public String price;
        public String stock;
    }

    public static class GuigesEntity implements Serializable {
        /*
        "title": "颜色",
		"guigeArray": ["绿色", "红色"]
         */
        public String title;
        public List<String> guigeArray= new ArrayList<>();
    }

}
