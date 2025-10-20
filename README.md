项目布局概述  
===
本项目包含多种Android界面组件实现，通过四个Activity展示了ListView、AlertDialog、菜单及ActionMode等关键组件的用法。

布局文件详情
---
1.Android ListView的用法  
Aty_SimpleAdapter 是一个使用 SimpleAdapter 展示动物列表并支持通知功能的 Activity，其布局文件 simpleadpter.xml 包含一个占满屏幕的 ListView（id 为 lv_animals）。列表项布局采用 animal.xml（水平布局，左侧为 TextView 显示动物名称，权重 1；右侧为 ImageView 显示动物图片，大小 100dp×100dp）。Activity 初始化时会创建包含狮子、老虎、猴子等动物名称及对应图片资源的数据列表，通过 SimpleAdapter 将数据与布局绑定。点击列表项会显示 Toast 提示选中的动物名称，并发送通知（需申请 POST_NOTIFICATIONS 权限），通知使用创建的 “动物通知” 渠道，包含标题、内容，点击可返回当前 Activity，且兼容不同系统版本的 PendingIntent 标记位设置。  
2.创建自定义布局的AlertDialog  
Aty_AlertDialog 用于展示自定义对话框，其布局文件 alertdialog.xml 包含一个按钮（Show Custom Dialog）。点击该按钮时，会加载 login.xml 布局作为对话框内容，login.xml 包含顶部标题栏、用户名输入框、密码输入框以及底部的 “Cancel” 和 “Sign in” 按钮。对话框中，点击 “Cancel” 按钮会关闭对话框，点击 “Sign in” 按钮会获取输入的用户名和密码后关闭对话框。  
3.使用XML定义菜单  
Aty_MenuTest 是一个测试菜单功能的 Activity，其布局文件 menutest.xml 包含顶部的Toolbar（标题为 “MenuTest”）和下方的TextView（文本为 “用于测试的内容”，初始大小 16sp）。该 Activity 将 Toolbar 设置为 ActionBar，并加载 menu_main.xml 菜单资源，菜单包含 “字体大小” 子菜单（含小、中、大三个选项，分别将文本大小设为 10sp、16sp、20sp）、“字体颜色” 子菜单（含红色、黑色选项，分别将文本颜色设为红色、黑色）以及 “普通菜单项”（点击显示 Toast 提示），通过点击菜单项可实时调整 TextView 的样式。  
4.创建上下文操作模式(ActionMode)的上下文菜单  
Aty_ActionMode 是一个实现列表项多选及批量操作功能的 Activity，其布局文件 action_mode.xml 仅包含一个占满屏幕的ListView用于展示数据。该Activity初始化时会创建包含图标和文本的列表数据（使用 ic_launcher 作为图标，文本为 "One" 到 "Five"），并通过自定义的 MyAdapter 适配数据，列表项布局采用 itemlist.xml（水平布局，左侧为 ImageView 显示图标，右侧为 TextView 显示文本）。长按列表项可启动 ActionMode 进入多选模式，选中项背景色变为 #33B5E5，顶部会显示选中数量，通过 ActionMode 菜单中的 “删除” 按钮可批量删除选中项，删除后列表会实时更新。
