package game;


import game.controllers.SceneController;
import game.gameobj.Player;
import game.scene.EndingScene;
import game.scene.InternetScene;
import game.scene.MenuScene;
import game.utils.*;
import game.utils.CommandSolver.KeyListener;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.io.IOException;

public class GI implements GameKernel.GameInterface, CommandSolver.MouseCommandListener, CommandSolver.KeyListener {

    public GI() throws IOException { // 應該可以不用丟錯誤
        SceneController.instance().change(new MenuScene());
    }
        


    @Override
    public void paint(Graphics g) { //繪畫
        SceneController.instance().paint(g);
    }

    @Override
    public void update() { //邏輯
        SceneController.instance().update();
    }

    @Override
    public void mouseTrig(MouseEvent e, CommandSolver.MouseState state, long trigTime) { // 滑鼠觸發
        if (SceneController.instance().mouseListener() != null) { // 若場控ml有回傳值
            SceneController.instance().mouseListener().mouseTrig(e, state, trigTime); // 此回傳值使用mT方法
        }
    }

    @Override
    public void keyPressed(int commandCode, long trigTime) { // 鍵按下
        KeyListener kl = SceneController.instance().keyListener();
        if (kl != null) {
            kl.keyPressed(commandCode, trigTime);
        }
    }

    @Override
    public void keyReleased(int commandCode, long trigTime) { // 鍵放開
        KeyListener kl = SceneController.instance().keyListener();
        if (kl != null) {
            kl.keyReleased(commandCode, trigTime);
        }
    }

    @Override
    public void keyTyped(char c, long trigTime) { // 鍵打字
        if (SceneController.instance().keyListener() != null) {
            SceneController.instance().keyListener().keyTyped(c, trigTime);
        }
    }

}
