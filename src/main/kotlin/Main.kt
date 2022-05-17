import javafx.animation.Animation
import javafx.animation.KeyFrame
import javafx.animation.Timeline
import javafx.application.Application
import javafx.application.Platform
import javafx.scene.Scene
import javafx.scene.layout.VBox
import javafx.stage.Stage
import javafx.util.Duration
import kotlin.system.exitProcess

class Main : Application() {
    override fun start(stage: Stage?) {
        val model = Model()

        // Timeline for infinite animation at a rate of 1 frame per second
        val timeline = Timeline(
            KeyFrame(Duration.seconds(1.0), {
                model.updateBoards()
            })
        )
        timeline.cycleCount = Animation.INDEFINITE

        val toolbar = ToolbarView(model, timeline)
        val grid = GridView(model)
        val status = StatusView(model)

        // Register views with the model
        model.addView(toolbar)
        model.addView(grid)
        model.addView(status)

        // Root of the scene graph
        val root = VBox()
        root.children.addAll(toolbar, grid, status)

        // Stage setup and display
        stage?.scene = Scene(root)
        stage?.isResizable = false
        stage?.maxWidth = 1600.0
        stage?.maxHeight = 1200.0
        stage?.title = "Conway’s Game of Life (esenmaju)"
        stage?.show()
        stage?.setOnCloseRequest {
            timeline.stop()
            Platform.exit()
            exitProcess(0)
        }

        // Start animation
        timeline.play()
    }
}