import javafx.scene.layout.GridPane
import javafx.scene.paint.Color
import javafx.scene.shape.Rectangle

// Acknowledgement: This GridView code is based closely on the "07.Layout/06.gridpane" sample code from the cs349 public repo

class GridView(model: Model) : IView, GridPane() {
    init {
        this.isGridLinesVisible = true
        this.hgap = 1.0
        this.vgap = 1.0

        for (row in 0 until model.numOfRows) {
            for (col in 0 until model.numOfCols) {
                val rect = Rectangle(12.0, 12.0)
                rect.fill = Color.WHITE
                rect.setOnMouseClicked {
                    model.addShape(row, col)
                }
                this.add(rect, col, row)
            }
        }
    }

    override fun update(model: Model) {
        var cellIsLive : Boolean
        for (rect in this.children) {
            if (rect is Rectangle) {
                cellIsLive = model.currentBoard[getRowIndex(rect)][getColumnIndex(rect)]
                if (cellIsLive && rect.fill == Color.WHITE) rect.fill = Color.BLACK
                else if (!cellIsLive && rect.fill == Color.BLACK) rect.fill = Color.WHITE
            }
        }
    }
}