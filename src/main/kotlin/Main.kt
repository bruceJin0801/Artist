import javafx.application.Application
import javafx.application.Platform
import javafx.geometry.Insets
import javafx.scene.Scene
import javafx.scene.control.*
import javafx.scene.layout.BorderPane
import javafx.scene.layout.VBox
import javafx.stage.FileChooser
import javafx.stage.Stage
import java.awt.event.KeyEvent.KEY_RELEASED

class Main : Application() {
    @Throws(Exception::class)
    override fun start(stage: Stage) {

        val menubar = MenuBar()
        val fileMenu = Menu("File")
        val fileNew = MenuItem("New")
        val fileSave = MenuItem("Save")
        val fileLoad = MenuItem("Load")
        val fileQuit = MenuItem("Quit")
        val helpMenu = Menu("Help")
        val helpAbout = MenuItem("About")
        fileMenu.items.addAll(fileNew, fileSave, fileLoad, fileQuit)
        helpMenu.items.addAll(helpAbout)
        menubar.menus.addAll(fileMenu, helpMenu)
        // event
        val model = Model()
        val scroll = ScrollPane()
        //val menu = MenubarView(model)
        val tool = ToolbarView(model)
        val canvas = CanvasView(model)
        // setup grid with views
        val board = BorderPane()
        val group1 = VBox()
        val group2 = VBox()
        val group3 = VBox()
        val group4 = VBox()
        group1.spacing = 5.0
        group1.padding = Insets(5.0)
        group2.spacing = 5.0
        group2.padding = Insets(5.0)
        group1.children.add(menubar)
        group2.children.add(tool)
        group3.children.add(canvas)
        board.padding =  Insets(5.0)
        scroll.content = group3
        /*scroll.viewportBoundsProperty().addListener { _, _, newValue ->
            group3.setPrefSize(newValue.width, newValue.height)
        }*/
        board.top = group1
        board.left = group2
        board.center = scroll
        val scene = Scene(board,960.0, 720.0)
        stage.maxHeight = 1440.0
        stage.minHeight = 480.0
        stage.maxWidth = 1920.0
        stage.minWidth = 640.0
        stage.title = "Sketch it"
        helpAbout.setOnAction {
            val alert = Alert(Alert.AlertType.INFORMATION)
            alert.title = "Info"
            alert.headerText = "Assignment2 : Sketch it !"
            alert.contentText = "Chengyu Jin" + "20874575"
            alert.showAndWait()
        }
        fileQuit.setOnAction { Platform.exit() }
        fileNew.setOnAction {
            CanvasView(model).children.clear()
            canvas.children.clear()
            canvas.children.add(CanvasView(model))
        }

        fileSave.setOnAction {
            var fc = FileChooser()
            val file = fc.showSaveDialog(stage)
        }
        fileLoad.setOnAction {
            var fc = FileChooser()
            fc.showOpenDialog(stage)
        }
        stage.scene = scene
        stage.show()
    }
}

