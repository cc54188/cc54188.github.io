package game.controllers;

import game.utils.Global;
import java.awt.Image;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import javax.imageio.ImageIO;

public class ImgController {

    // 內容
    private HashMap<String, Image> imgPairs; // 雜湊 取代ArrayList<KeyPair>

    public ImgController() {
        imgPairs = new HashMap<>(); // 雜湊
    }

    public Image tryGetImage(String path) {
        if (!imgPairs.containsKey(path)) {  // 若imgPiars裡找不到path
            return addImage(path);       // 新增，加入，回傳
        }
        return imgPairs.get(path); // 找到 => 直接回傳
    }

    private Image addImage(String path) {
        try {
            if (Global.IS_DEBUG) {
//                System.out.println("load img from: " + path);
            }
            Image img = ImageIO.read(getClass().getResource(path)); // 取出此路徑的圖
            imgPairs.put(path, img);  // add?
            return img;
        } catch (IOException e) {   // 錯誤的話
            e.printStackTrace();  // 印出此訊息
        }
        return null;
    }
    
    public void clear(){  // 清除
        imgPairs.clear();
    }

    // 做標記 => 標記哪些資源是這個場景開始後才載入
}
