**Conway’s Game of Life Emulator**

**Description**: An emulator for playing Conway's Game of Life, an automated board game created by the mathematician John Horton Conway.

**Rules**:
The board of the Game of Life is a two-dimensional grid of square cells, each in one of two possible states, live or dead. Every cell is affected by its eight adjacent neighbouring cells on an ongoing basis, once per second. Every second, the following transitions are automatically applied:
- Any live cell with two or three live neighbours stays live.
- Any dead cell with three live neighbours becomes live.
- All other live cells become dead.
- All other dead cells stay dead. 
As time progresses, the automatic transitions will cause the live cells on the board to change in unexpected ways and form interesting patterns.

**Controls**:
The player is able to add live cells to the board at any time by selecting one of five shape buttons, and clicking anywhere on the board to place that shape of live cells.
The board can also be cleared (i.e. reset all cells to dead) by clicking the Clear button.

**Drawing Mode**:
There is an additional "Enable/Disable Drawing Mode" button, beside the "Clear" button, that enables/disables drawing mode.
When drawing mode is enabled, the animation is paused to prevent cells from animating while you are attempting to draw.
During drawing mode, the mouse cursor can draw arbitrary shapes by drawing and erasing individual cells. Clicking on a dead cell will make it live, while clicking on a live cell will make it dead.
The "Clear" button and 5 shape buttons still work as usual during drawing mode. The 5 shapes can also be manually drawn by drawing individual cells in the same pattern.
If a shape button is selected during drawing mode and you wish to return to drawing with individual cells, click the "Custom (Cell)" button which is only visible during drawing mode.
When you are done drawing, the same "Enable/Disable Drawing Mode" button can be clicked to disable drawing mode, which resumes the animation.

**Sample Screenshots**:

Classic Gameplay Example

![Conway’s Game of Life Emulator 1](https://user-images.githubusercontent.com/55682406/168740758-da02856f-6e08-4632-8a53-c6420125e5a0.png)

Drawing Mode Example

![Conway’s Game of Life Emulator 2](https://user-images.githubusercontent.com/55682406/168740779-825e8e98-b0b5-4a32-874a-0ce8e304399a.png)

**Developers**: Emon Sen Majumder

**Developed using**: kotlinc-jvm 1.5.21 (OpenJDK 11.0.12+7), JavaFX

**Tested on**: macOS 10.14.6 (MacBook Air 2015)

**Notes**:

1. Shapes are created with the mouse click location representing the top left cell (live or dead) of the respective shape's button graphic.
