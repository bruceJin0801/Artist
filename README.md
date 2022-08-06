# Artist
The main area of your drawing program is a canvas where users can draw shapes. Users can select a shape from the list on the side, specify colour, border thickness and style, then use their mouse to draw that shape on the canvas.

<h2>File:</h2>

<ins>**New:**</ins> create a new blank drawing (and prompt the user to save if the current drawing is unsaved).

<ins>**Load:**</ins>  load a drawing that you previously saved (also prompt the user before discarding the current drawing).

<ins>**Save:**</ins>  save the current drawing using a file format that you determine.

<ins>**Quit:**</ins>  exit the application (prompt the user to save before exiting).

<ins>**About:**</ins>  display a dialog box with the application name, your name and WatID.

<h2>selection:</h2> 

<ins>**Selection tool:**</ins>  allows the user to select a shape that has been drawn. To select a shape, the user should click this tool, then click on an existing shape (resulting in some visual indication to the shape itself to indicate that its been selected). Pressing ESC on the keyboard (or clicking an empty part of the canvas) will clear shape selection. Selecting a shape will cause the colour palette, line thickness and style to update their state to reflect the currently selected shape, Changing colour, line thickness or line style when a shape is selected will update the shape to the new values.

<ins>**Erase tool:**</ins> click on this tool and then click on a shape to remove it. Pressing DEL while a shape is selected should also delete the selected shape.

<ins>**Line drawing tool:**</ins> select this to draw a line using the selected properties.

<ins>**Circle drawing tool:**</ins> select this to draw a circle at the point indicated using the selected properties.

<ins>**Rectangle tool:**</ins> select this to draw a rectangle using the selected properties.

<ins>**Fill tool:**</ins> select this tool, and click on a shape to change it’s fill colour to the currently selected value.

<ins>**Line colour icon:**</ins> graphically displays the currently selected line color. Clicking on this object will bring up a color chooser dialog that lets the user pick a color. This colour will be used as the border colour for any new shapes that are drawn (or the ONLY colour in the case of a line).

<ins>**Fill colour icon:**</ins> graphically displays the currently selected fill color. Clicking on this object will bring up a color chooser dialog that lets the user pick a color. This colour will be used as the fill colour for any new shapes that are drawn (it will NOT be used if a line is being drawn).

<ins>**Line thickness palette:**</ins> a graphical display of at least three line widths that the user can select. Selecting a line width will set the line thickness or border thickness for any new shapes drawn.

<ins>**Line style palette:**</ins> a graphical display of solid or dotted lines with at least least three choices. Selecting a line style will set the line style or border style for any new shapes that are drawn.

<h2>Functions:</h2>

<ins>**Drawing:**</ins> The user draws a shape by selecting the appropriate tool, and clicking on the canvas where they want to start drawing it. The first click typicaly sets the upper-left corner starting position of the shape, and a “preview” is shown as the mouse is dragged. When the mouse button is released, that completes the shape (i.e. the drag operation sets the width of the circle, or creates a line, depending on the shape being drawn, and the shape is completed when the mouse button is released).

<ins>**Selection:**</ins> The user should be able to select any shape and move it by dragging it with the mouse. Shapes can overlap, with the more recently drawn shape overlapping the older shape (hint: you order them back-to-front as they are created). Your interface should clearly indicate which shape, if any, is selected. You may use thicker borders around the tool, or some other visual indicator that you choose. When a shape is selected, the properties should also change to reflect the shape’s properties.

<ins>**Update shape:**</ins> Changing the properties of a selected shape should change that shape’s properties.

<ins>**Window resizing:**</ins> support dynamic resizing of the application window, so that the application adapts to different window sizes and resolutions. If you change the size of the window, the shapes will stay at the same size and position.

<ins>**Scale shapes:**</ins> change the scale/size of any shape by selecting it, then grabbing a corner of the shape, and dragging to increase/decrease it’s size.

<ins>**Rotate shapes:**</ins> the ability to grab a corner of a shape and rotate it in real-time.
