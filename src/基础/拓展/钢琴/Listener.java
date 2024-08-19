package 基础.拓展.钢琴;

import com.github.kwhat.jnativehook.GlobalScreen;
import com.github.kwhat.jnativehook.NativeHookException;
import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent;
import com.github.kwhat.jnativehook.keyboard.NativeKeyListener;
import com.github.kwhat.jnativehook.mouse.NativeMouseEvent;
import com.github.kwhat.jnativehook.mouse.NativeMouseListener;

import java.util.function.Consumer;

/**
 鼠标键盘监听器
 */
public class Listener implements NativeKeyListener, NativeMouseListener {

    Consumer<Integer> keyPressed;
    Consumer<Integer> mousePressed;

    /**
     例如:keyPressed = code -> {
     <p>
     }

     @param keyPressed 事件处理函数
     */
    public Listener(Consumer<Integer> keyPressed, Consumer<Integer> mousePressed) {
        register();
        setPressed(keyPressed, mousePressed);
        setListener();
    }

    private void register() {
        try {
            GlobalScreen.unregisterNativeHook();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            GlobalScreen.registerNativeHook();
        } catch (NativeHookException ex) {
            System.err.println("键盘监听初始化发生错误: " + ex.getMessage());
            System.exit(1);
        }
    }


    public void setListener() {
        GlobalScreen.addNativeKeyListener(this);
        GlobalScreen.addNativeMouseListener(this);
    }

    public void nativeKeyPressed(NativeKeyEvent e) {
        if (keyPressed != null) keyPressed.accept(e.getKeyCode());
    }

    public void nativeMousePressed(NativeMouseEvent e) {
        if (mousePressed != null) mousePressed.accept(e.getModifiers());
    }

    public void setPressed(Consumer<Integer> keyPressed, Consumer<Integer> mousePressed) {
        this.keyPressed = keyPressed;
        this.mousePressed = mousePressed;
    }

    public static String getKeyText(int keyCode) {
        return NativeKeyEvent.getKeyText(keyCode);
    }

    public static String getMouseText(int modifiers) {
        return NativeMouseEvent.getModifiersText(modifiers);
    }

}
