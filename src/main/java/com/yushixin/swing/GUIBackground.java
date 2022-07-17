package com.yushixin.swing;

import javax.swing.*;
import java.awt.*;

public class GUIBackground {
    public static void main(String[] args) {
        // 背景图片
        ImageIcon icon = new ImageIcon("src/main/java/com/yushixin/swing/bg.gif");
        // 宽
        int width = icon.getIconWidth();
        // 高
        int height = icon.getIconHeight();

        // JFrame
        JFrame jf = new JFrame("测试GIF背景图片");
        // 设置窗口尺寸
        jf.setSize(width, height);
        // 设置窗口居中
        jf.setLocationRelativeTo(null);
        // 设置点击关闭按钮时退出
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // 1.分层面板
        JLayeredPane layeredPane = new JLayeredPane();
        // 2.背景图片面板
        JPanel bgPanel = new JPanel();
        // 设置面板流式布局
        bgPanel.setLayout(new FlowLayout());
        // 把图片添加到标签中
        JLabel label = new JLabel(icon);
        // 设置尺寸
        bgPanel.setBounds(0, 0, width, height);
        // 添加标签
        bgPanel.add(label);

        // 3.按钮面板
        JPanel panel = new JPanel();
        // 设置面板流式布局
        panel.setLayout(new FlowLayout());
        panel.setBackground(Color.RED);
        // 面板设置为透明
        panel.setOpaque(false);
        // 测试按钮
        JButton button = new JButton("测试按钮");
        // 设置尺寸
        panel.setBounds(0, 0, width, height);
        // 面板添加测试按钮
        panel.add(button);

        // 背景图片面板添加到分层面板的低层
        layeredPane.add(bgPanel, JLayeredPane.DEFAULT_LAYER);
        // 背景图片面板添加到分层面板的高层
        layeredPane.add(panel, JLayeredPane.MODAL_LAYER);

        // 添加分层面板
        jf.setContentPane(layeredPane);
        // 设置显示窗口
        jf.setVisible(true);
    }
}
