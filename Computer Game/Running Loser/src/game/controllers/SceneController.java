package game.controllers;

import game.scene.Scene;
import game.utils.CommandSolver;
import java.awt.Graphics;

public class SceneController {

    private static SceneController sceneController; // 自己的靜態實體

    private SceneController() {
        lastIrc = new ImgController(); // 建構last圖控
        currentIrc = new ImgController(); // 建構當前圖控
    }

    public static SceneController instance() { // 單例
        if (sceneController == null) {         // 沒實體的話
            sceneController = new SceneController(); // 建構
        }
        return sceneController; // 有的話就不用再建，直接回傳
    }

    private Scene lastScene; // 上個場景
    private Scene currentScene; // 當前場景
    private ImgController lastIrc; // last圖控(其他)
    private ImgController currentIrc; // 當前圖控

    public void change(Scene scene) { // 換場
        lastScene = currentScene; // 現場成為待清場景

        ImgController tmp = currentIrc; // 交換圖控
        currentIrc = lastIrc;
        lastIrc = tmp;

        if (scene != null) {
            scene.sceneBegin();
        }
        currentScene = scene;
    }

    public void paint(Graphics g) {
        if (currentScene != null) {    // 有現場就畫
            currentScene.paint(g);
        }
    }

    public void update() {            
        if (lastScene != null) {          // 有上一場
            lastScene.sceneEnd();         // 關掉
            lastIrc.clear();             // 清除上場景圖片(不會變成null)
            lastScene = null;            // 上場景變null
        }
        if (currentScene != null) {       // 有現場就更新
            currentScene.update();        
        }
    }

    public CommandSolver.MouseCommandListener mouseListener() {
        return currentScene.mouseListener();  // 回傳現場滑鼠監聽
    }

    public CommandSolver.KeyListener keyListener() {
        return currentScene.keyListener();  // 回傳現場鍵盤監聽
    }

    public ImgController irc() {
        return currentIrc;     // 回傳現場圖控(就可使用圖控方法)
    }
}