import javafx.animation.Timeline
import javafx.scene.control.*
import javafx.scene.image.Image
import javafx.scene.image.ImageView

class ToolbarView(model: Model, timeline: Timeline) : IView, ToolBar() {
    init {
        // Block Button
        val blockImageView = ImageView(Image("BlockIcon.png"))
        blockImageView.isPreserveRatio = true
        blockImageView.fitHeight = 22.0
        val blockButton = Button("Block", blockImageView)
        blockButton.setOnAction {
            model.setShape("Block")
        }

        // Beehive Button
        val beehiveImageView = ImageView(Image("BeehiveIcon.png"))
        beehiveImageView.isPreserveRatio = true
        beehiveImageView.fitHeight = 22.0
        val beehiveButton = Button("Beehive", beehiveImageView)
        beehiveButton.setOnAction {
            model.setShape("Beehive")
        }

        // Blinker Button
        val blinkerImageView = ImageView(Image("BlinkerIcon.png"))
        blinkerImageView.isPreserveRatio = true
        blinkerImageView.fitWidth = 35.0
        val blinkerButton = Button("Blinker", blinkerImageView)
        blinkerButton.prefHeight = 32.0
        blinkerButton.setOnAction {
            model.setShape("Blinker")
        }

        // Toad Button
        val toadImageView = ImageView(Image("ToadIcon.png"))
        toadImageView.isPreserveRatio = true
        toadImageView.fitHeight = 22.0
        val toadButton = Button("Toad", toadImageView)
        toadButton.setOnAction {
            model.setShape("Toad")
        }

        // Glider Button
        val gliderImageView = ImageView(Image("GliderIcon.png"))
        gliderImageView.isPreserveRatio = true
        gliderImageView.fitHeight = 22.0
        val gliderButton = Button("Glider", gliderImageView)
        gliderButton.setOnAction {
            model.setShape("Glider")
        }

        // Clear Button
        val clearImageView = ImageView(Image("ClearIcon.png"))
        clearImageView.isPreserveRatio = true
        clearImageView.fitHeight = 22.0
        val clearButton = Button("Clear", clearImageView)
        clearButton.setOnAction {
            model.clearCurrentBoard()
        }

        // Cell Button
        val cellImageView = ImageView(Image("CellIcon.png"))
        cellImageView.isPreserveRatio = true
        cellImageView.fitWidth = 12.0
        val cellButton = Button("Custom (Cell)", cellImageView)
        cellButton.prefHeight = 32.0
        cellButton.setOnAction {
            model.setShape("Cell")
        }

        // Drawing Mode Button
        val drawingImageView = ImageView(Image("DrawIcon.png"))
        drawingImageView.isPreserveRatio = true
        drawingImageView.fitHeight = 22.0
        val drawButton = Button("Enable Drawing Mode", drawingImageView)
        drawButton.setOnAction {
            if (drawButton.text == "Enable Drawing Mode") {
                timeline.stop()
                drawButton.text = "Disable Drawing Mode"
                this.items.add(cellButton)
                model.setShape("Cell")
            } else if (drawButton.text == "Disable Drawing Mode") {
                timeline.play()
                drawButton.text = "Enable Drawing Mode"
                this.items.remove(cellButton)
                if (model.currentShape == "Cell") model.setShape("Please select a shape")
            }
        }

        // Add all buttons to toolbar
        this.items.addAll(blockButton, beehiveButton, blinkerButton, toadButton, gliderButton)
        this.items.addAll(Separator(), clearButton, Separator(), drawButton)
    }

    override fun update(model: Model) {}
}