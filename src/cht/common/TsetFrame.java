package cht.common;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import java.awt.Color;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

import java.io.File;
import java.io.IOException;

import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JPopupMenu;

import java.awt.Component;
import java.util.regex.Pattern;

import javax.swing.border.CompoundBorder;

import javax.swing.JMenuItem;

import cht.function.Copy;
import cht.function.CreateFile;
import cht.function.DeleteFolder;
import cht.function.Encryption;
import cht.function.Write;
import cht.function.ZipUtil;


@SuppressWarnings("serial")
public class TsetFrame extends JFrame {

    /*
     * 全局路径
     */
    static Path path = new Path();

    private JPanel contentPane;

    //眼不见心不烦
    private JLabel label = new JLabel("请选择盘符：");

    private JLabel label_1 = new JLabel("当前路径为：");

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                TsetFrame frame = new TsetFrame();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    @SuppressWarnings({"rawtypes"})
    private TsetFrame() {
        setBackground(Color.CYAN);
        setTitle("MyFileManager");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 551, 468);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);


        //默认显示
        JLabel lblNewLabel = new JLabel();
        lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 14));

        //显示文件
        JPanel panel = new JPanel();
        panel.setBackground(Color.WHITE);
        contentPane.add(panel, BorderLayout.CENTER);

        JList fileList = new JList();
        fileList.setBorder(new CompoundBorder());
        fileList.setBackground(Color.LIGHT_GRAY);
        fileList.setFont(new Font("宋体", Font.PLAIN, 20));

        fileList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    File temp;
                    if (String.valueOf(path.getPath().charAt(path.getPath().length() - 1)).equals(File.separator)) {
                        temp = new File(path.getPath() + fileList.getSelectedValue().toString());
                    } else {
                        temp = new File(path.getPath() + File.separator + fileList.getSelectedValue().toString());
                    }
                    if (temp.isDirectory()) {
                        path.updatePath(fileList.getSelectedValue().toString());
                        showList(fileList);
                        lblNewLabel.setText(path.getPath());
                        //System.out.println(path.getPath());
                    } else if (temp.isFile()) {
                        System.out.println("is txt");
                        String pattern = "[\\w.]*\\.txt";
                        //System.out.println(temp.getName());
                        //String fileName = temp.getName();
                        if (Pattern.matches(pattern, temp.getName())) {
                            System.out.println("is input");
                            String str = JOptionPane.showInputDialog("请输入内容");
                            Write write = new Write();
                            write.wirte(temp, str);
                        }
                    }
                } else {
                    //showList(fileList);
                }
            }
        });
        panel.add(fileList);


        //按钮类
        JButton btnC = new JButton("C盘");
        btnC.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent arg0) {
                path.setPath("C:" + File.separator);
                lblNewLabel.setText((new File(path.getPath())).getAbsolutePath());
                showList(fileList);
            }
        });

        JButton btnD = new JButton("D盘");
        btnD.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent arg0) {
                path.setPath("D:" + File.separator);
                lblNewLabel.setText((new File(path.getPath())).getAbsolutePath());
                showList(fileList);

            }
        });

        JButton btnE = new JButton("E盘");
        btnE.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent arg0) {
                path.setPath("E:" + File.separator);
                lblNewLabel.setText(String.valueOf((new File(path.getPath()))));
                showList(fileList);
            }
        });

        JButton btnF = new JButton("F盘");
        btnF.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent arg0) {
                path.setPath("F:" + File.separator);
                lblNewLabel.setText((new File(path.getPath())).getAbsolutePath());
                showList(fileList);


            }
        });


        JButton btnBack = new JButton("BACK");
        btnBack.addActionListener(e -> {
            File file = new File(path.getPath());
            if (file.getParentFile() != null) {
                path.returnBack();
                lblNewLabel.setText((new File(path.getPath())).getAbsolutePath());
                showList(fileList);
            }
        });


        GroupLayout gl_contentPane = new GroupLayout(contentPane);
        gl_contentPane.setHorizontalGroup(
                gl_contentPane.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_contentPane.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                                        .addComponent(panel, GroupLayout.DEFAULT_SIZE, 505, Short.MAX_VALUE)
                                        .addGroup(gl_contentPane.createSequentialGroup()
                                                .addComponent(label)
                                                .addPreferredGap(ComponentPlacement.UNRELATED)
                                                .addComponent(btnC)
                                                .addGap(18)
                                                .addComponent(btnD)
                                                .addGap(18)
                                                .addComponent(btnE)
                                                .addGap(18)
                                                .addComponent(btnF)
                                                .addPreferredGap(ComponentPlacement.RELATED, 108, Short.MAX_VALUE)
                                                .addComponent(btnBack))
                                        .addGroup(gl_contentPane.createSequentialGroup()
                                                .addComponent(label_1)
                                                .addPreferredGap(ComponentPlacement.RELATED)
                                                .addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, 427, Short.MAX_VALUE)))
                                .addContainerGap())
        );
        gl_contentPane.setVerticalGroup(
                gl_contentPane.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_contentPane.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(label)
                                        .addComponent(btnC)
                                        .addComponent(btnD)
                                        .addComponent(btnE)
                                        .addComponent(btnF)
                                        .addComponent(btnBack))
                                .addPreferredGap(ComponentPlacement.RELATED)
                                .addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(label_1)
                                        .addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(ComponentPlacement.RELATED)
                                .addComponent(panel, GroupLayout.PREFERRED_SIZE, 350, Short.MAX_VALUE)
                                .addContainerGap())
        );

        JScrollPane scrollPane = new JScrollPane(fileList);

        JPopupMenu popupMenu = new JPopupMenu();
        addPopup(fileList, popupMenu);

        JMenuItem mntmNewFile = new JMenuItem("新文件");
        mntmNewFile.addActionListener(e -> {
            String str = JOptionPane.showInputDialog("请输入文件名(带后缀)");
            try {
                CreateFile createFile = new CreateFile();
                if (createFile.createFile(new File(path.getPath() + File.separator + str))) {

                    showList(fileList);
                }
            } catch (IOException e1) {
                e1.printStackTrace();
            } finally {
                showList(fileList);
            }
        });
        popupMenu.add(mntmNewFile);

        JMenuItem mntmNewFolder = new JMenuItem("新文件夹");
        mntmNewFolder.addActionListener(e -> {
            String str = JOptionPane.showInputDialog("请输入文件夹名");
            CreateFile createFile = new CreateFile();
            if (createFile.mkdir(new File(path.getPath() + File.separator + str + File.separator))) {
                showList(fileList);
            }
            showList(fileList);
        });
        popupMenu.add(mntmNewFolder);

        JMenuItem mntmDelect = new JMenuItem("删除");
        mntmDelect.addActionListener(e -> {
            String fileName = fileList.getSelectedValue().toString();
            File file = new File(path.getPath() + File.separator + fileName);
            DeleteFolder deleteFolder = new DeleteFolder();
            if (file.exists() && file.isDirectory()) {
                deleteFolder.deleteDirectory(file);
            } else if (file.exists() && file.isFile()) {
                deleteFolder.deleteFile(file);
            }
            showList(fileList);
        });
        popupMenu.add(mntmDelect);

        /**
         * 复制粘贴
         * @param flag 控制paste的功能
         * @param copyPath 源文件的路径
         * @param pastePath 输入的路径
         */


        Path copyFilePath = new Path();
        Path copyFolderPath = new Path();
        //Path pastePath = new Path();
        JMenuItem mntmCopy = new JMenuItem("复制");

        JMenuItem mntmPaste = new JMenuItem("粘贴");
        mntmPaste.setEnabled(false);


        mntmCopy.addActionListener(e -> {
            String fileName = fileList.getSelectedValue().toString();
            File file = new File(path.getPath() + File.separator + fileName);
            if (file.exists() && file.isFile()) {
                copyFilePath.setPath(file.getAbsolutePath());
                mntmPaste.setEnabled(true);
                copyFolderPath.setPath(null);
            } else if (file.exists() && file.isDirectory()) {
                copyFolderPath.setPath(file.getAbsolutePath());
                copyFilePath.setPath(null);
                mntmPaste.setEnabled(true);
            }
        });

        mntmPaste.addActionListener(arg0 -> {

            File file = new File(path.getPath());

            if (copyFilePath.getPath() != null) {
                File copyName = new File(copyFilePath.getPath());
                try {
                    Copy copy = new Copy();
                    copy.copyFile(copyFilePath.getPath(), file.getAbsolutePath() + File.separator + copyName.getName());
                    showList(fileList);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else if (copyFolderPath.getPath() != null) {
                try {
                    File file1 = new File(path.getPath());
                    Copy copy = new Copy();
                    copy.copyDir(copyFolderPath.getPath(), file1.getAbsolutePath());
                    System.out.println(path.getPath());
                    showList(fileList);

                } catch (IOException e) {

                    e.printStackTrace();
                }
            }
        });


        popupMenu.add(mntmCopy);
        popupMenu.add(mntmPaste);

        /*
         * 复制粘贴结束
         */

        /*
         * 加密解密
         * @param password 秘钥
         */
        JMenuItem mntmEncrypted = new JMenuItem("加密");
        mntmEncrypted.addActionListener(e -> {
            String str = path.getPath() + File.separator + fileList.getSelectedValue().toString();
            File file = new File(str);
            if (file.isDirectory() && file.exists()) {
                JOptionPane.showMessageDialog(null, "无法加密的类型");
            } else if (file.exists() && file.isFile()) {
                Encryption temp = new Encryption();
                try {
                    temp.encrypt(file);
                    System.out.println("加密成功");
                    showList(fileList);
                } catch (Exception e1) {
                    System.out.println("加密失败");
                    e1.printStackTrace();
                }
            }
        });
        popupMenu.add(mntmEncrypted);

        JMenuItem mntmDecrypted = new JMenuItem("解密");
        mntmDecrypted.addActionListener(e -> {
            String str = path.getPath() + File.separator + fileList.getSelectedValue().toString();
            File file = new File(str);
            if (file.isDirectory() && file.exists()) {
                JOptionPane.showMessageDialog(null, "无法解密的类型");
            } else if (file.exists() && file.isFile()) {
                Encryption temp = new Encryption();
                try {
                    temp.decrypt(file);
                    System.out.println("解密成功");
                    showList(fileList);
                } catch (Exception e1) {
                    System.out.println("解密失败");
                    e1.printStackTrace();
                }
            }
        });
        popupMenu.add(mntmDecrypted);

        JMenuItem mntmCompress = new JMenuItem("压缩");
        mntmCompress.addActionListener(e -> {
            String str = path.getPath() + File.separator + fileList.getSelectedValue().toString();
            File file = new File(str);
            String name = JOptionPane.showInputDialog("请输入压缩文件的名称(无需后缀名)");
            try {
                if (name.equals("")) {
                    ZipUtil zipUtil = new ZipUtil();
                    if (zipUtil.zipMultiFile(file, name, true)) {
                        JOptionPane.showMessageDialog(null, "压缩成功");
                        showList(fileList);
                    } else {
                        JOptionPane.showMessageDialog(null, "压缩失败");
                    }
                } else {
                    System.out.println("取消压缩");
                }
            } catch (Exception e1) {

                e1.printStackTrace();
            }
        });
        popupMenu.add(mntmCompress);

        JMenuItem mntmDecompress = new JMenuItem("解压");
        mntmDecompress.addActionListener(e -> {
            String str = path.getPath() + File.separator + fileList.getSelectedValue().toString();
            File file = new File(str);
            try {
                if (file.isFile()) {
                    String out = JOptionPane.showInputDialog("请输入解压后文件夹的名称");
                    CreateFile createFile = new CreateFile();
                    createFile.mkdir(new File(path.getPath() + File.separator + out));
                    if (new ZipUtil().unZip(file, new File(path.getPath() + File.separator + out))) {
                        JOptionPane.showMessageDialog(null, "解压成功");
                        showList(fileList);
                    } else {
                        JOptionPane.showMessageDialog(null, "解压失败");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "不是压缩文件！");
                }
            } catch (Exception e1) {

                e1.printStackTrace();
            }
        });
        popupMenu.add(mntmDecompress);



        /*
         *加密解密结束
         *
         */

        GroupLayout gl_panel = new GroupLayout(panel);
        gl_panel.setHorizontalGroup(
                gl_panel.createParallelGroup(Alignment.TRAILING)
                        .addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 515, Short.MAX_VALUE)
        );
        gl_panel.setVerticalGroup(
                gl_panel.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_panel.createSequentialGroup()
                                .addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 350, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panel.setLayout(gl_panel);
        contentPane.setLayout(gl_contentPane);
    }


    @SuppressWarnings({"unchecked", "rawtypes"})
    private void showList(JList list) {
        if (path.fileList() != null) {
            String[] strings = path.fileList();
            System.out.println(path.getPath());
            list.setListData(strings);
        }
    }

    private void addPopup(Component component, final JPopupMenu popup) {
        component.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                if (e.isPopupTrigger()) {
                    showMenu(e);
                }
            }

            public void mouseReleased(MouseEvent e) {
                if (e.isPopupTrigger()) {
                    showMenu(e);
                }
            }

            private void showMenu(MouseEvent e) {
                popup.show(e.getComponent(), e.getX(), e.getY());
            }
        });
    }
}
