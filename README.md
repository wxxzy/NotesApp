# NotesApp

一个简单的笔记应用，用于记录和管理日常笔记。使用 Kotlin 和 Jetpack Compose 构建，支持 Android 平台。

## 功能特性

- **创建笔记**：用户可以创建新的笔记，并保存标题和内容。
- **编辑笔记**：用户可以编辑已存在的笔记。
- **删除笔记**：用户可以删除不需要的笔记。
- **笔记列表**：所有笔记以列表形式展示，方便浏览和管理。
- **主题切换**：支持浅色和深色主题切换。

## 技术栈

- **编程语言**：Kotlin
- **UI 框架**：Jetpack Compose
- **架构模式**：MVVM (Model-View-ViewModel)
- **数据存储**：Room 数据库
- **依赖注入**：Hilt
- **导航**：Navigation Component

## 项目结构

```
NotesApp/
├── app/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/nemo/notes/
│   │   │   │   ├── model/          # 数据模型
│   │   │   │   ├── repository/     # 数据仓库
│   │   │   │   ├── ui/             # UI 组件
│   │   │   │   ├── viewmodel/      # ViewModel
│   │   │   │   ├── di/             # 依赖注入
│   │   │   │   └── database/       # Room 数据库
│   │   │   └── res/                # 资源文件
│   │   └── test/                   # 单元测试
│   └── build.gradle                # 模块级构建配置
├── build.gradle                    # 项目级构建配置
└── settings.gradle                 # 项目设置
```

## 安装与运行

1. **克隆项目**：
   ```bash
   git clone https://github.com/wxxzy/NotesApp.git
   ```

2. **打开项目**：
   - 使用 Android Studio 打开项目文件夹。

3. **同步项目**：
   - 点击 `File -> Sync Project with Gradle Files`，等待 Gradle 同步完成。

4. **运行项目**：
   - 连接 Android 设备或启动模拟器。
   - 点击 `Run -> Run 'app'`，应用将安装并运行在设备上。

## 依赖项

- **Jetpack Compose**：用于构建现代 UI。
- **Room**：用于本地数据存储。
- **Hilt**：用于依赖注入。
- **Navigation Component**：用于应用内导航。
- **Coroutines**：用于异步编程。

## 贡献

欢迎贡献代码！如果你有任何改进建议或发现 bug，请提交 issue 或 pull request。

1. Fork 项目。
2. 创建你的分支 (`git checkout -b feature/YourFeature`)。
3. 提交更改 (`git commit -m 'Add some feature'`)。
4. 推送到分支 (`git push origin feature/YourFeature`)。
5. 提交 pull request。

## 许可证

本项目采用 [MIT 许可证](LICENSE)。

---

## 截图

![笔记列表](screenshots/note_list.png)
![编辑笔记](screenshots/note_edit.png)

## 联系方式

如有任何问题，请联系：  
- 邮箱：wxxlily@gmail.com  
- GitHub: [wxxzy](https://github.com/wxxzy)
```

