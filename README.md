# Artist
The main area of your drawing program is a canvas where users can draw shapes. Users can select a shape from the list on the side, specify colour, border thickness and style, then use their mouse to draw that shape on the canvas.

Your drawing program will have the following layout and features:

A menu bar containing the following menus and menu items:

<h2>File:</h2>
<int>New:<int> create a new blank drawing (and prompt the user to save if the current drawing is unsaved).
**Load**: load a drawing that you previously saved (also prompt the user before discarding the current drawing).
**Save**: save the current drawing using a file format that you determine.
**Quit**: exit the application (prompt the user to save before exiting).
**About**: display a dialog box with the application name, your name and WatID.

<h2>selection tool:</h2> 
this allows the user to select a shape that has been drawn. To select a shape, the user should click this tool, then click on an existing shape (resulting in some visual indication to the shape itself to indicate that its been selected). Pressing ESC on the keyboard (or clicking an empty part of the canvas) will clear shape selection. Selecting a shape will cause the colour palette, line thickness and style to update their state to reflect the currently selected shape (e.g. if I select a red circle with the largest line thickness, the colour palette should change to red, and the line thickness should change to the largest line to match the selected shape). Changing colour, line thickness or line style when a shape is selected will update the shape to the new values.
***Erase tool***: click on this tool and then click on a shape to remove it. Pressing DEL while a shape is selected should also delete the selected shape.
A line drawing tool: select this to draw a line using the selected properties.
A circle drawing tool: select this to draw a circle at the point indicated using the selected properties.
A rectangle tool: select this to draw a rectangle using the selected properties.
A fill tool: select this tool, and click on a shape to change it’s fill colour to the currently selected value.
A line colour icon that graphically displays the currently selected line color. Clicking on this object will bring up a color chooser dialog that lets the user pick a color. This colour will be used as the border colour for any new shapes that are drawn (or the ONLY colour in the case of a line).

A fill colour icon that graphically displays the currently selected fill color. Clicking on this object will bring up a color chooser dialog that lets the user pick a color. This colour will be used as the fill colour for any new shapes that are drawn (it will NOT be used if a line is being drawn).

A line thickness palette: a graphical display of at least three line widths that the user can select. Selecting a line width will set the line thickness or border thickness for any new shapes drawn.

A line style palette: a graphical display of solid or dotted lines with at least least three choices. Selecting a line style will set the line style or border style for any new shapes that are drawn.

The following functionality should be supported:

Drawing: The user draws a shape by selecting the appropriate tool, and clicking on the canvas where they want to start drawing it. The first click typicaly sets the upper-left corner starting position of the shape, and a “preview” is shown as the mouse is dragged. When the mouse button is released, that completes the shape (i.e. the drag operation sets the width of the circle, or creates a line, depending on the shape being drawn, and the shape is completed when the mouse button is released).
Selection: The user should be able to select any shape and move it by dragging it with the mouse. Shapes can overlap, with the more recently drawn shape overlapping the older shape (hint: you order them back-to-front as they are created). Your interface should clearly indicate which shape, if any, is selected. You may use thicker borders around the tool, or some other visual indicator that you choose. When a shape is selected, the properties should also change to reflect the shape’s properties.
Update shape: Changing the properties of a selected shape should change that shape’s properties.
Window resizing: Your application should support dynamic resizing of the application window, so that the application adapts to different window sizes and resolutions. If you change the size of the window, the shapes should stay at the same size and position (which implies that you may need scrollbars if the shapes extend past the edge of the window). You should set “reasonable” minimum and maximum sizes for your application window (e.g. range from 640x480 to 1920x1440). You can decide on the exact range but make sure that it clearly demonstrates a change in layout.
In all cases, your UI should disable controls when they are not appropriate (e.g. if no shape is selected and you click on the Select tool, the drawing property tools should be disabled since they don’t apply. Once you select a shape, they would then be enabled and changed to the properties for that shape).

You may make changes to this layout provided that you include all of this functionality in a consistent, usable and appealing manner. Note that there are 10 marks for aesthetics and design.

Choose Your Own Feature!
Select one or more features to implement from the list below, up to a maximum of 10 marks. If you implement more than 10 marks worth of features, we will ignore the extra features.

Scale shapes: the ability to change the scale/size of any shape by selecting it, then grabbing a corner of the shape, and dragging to increase/decrease it’s size (i.e. direct manipulation) (5 marks).
Rotate shapes: the ability to grab a corner of a shape and rotate it in real-time (i.e. direct manipulation). (5 marks)
Zoom and pan: support zooming the canvas in and out, scaling the contents and showing scrollbars as needed. This also requires the ability to pan around the screen if it’s zoomed in larger than the viewport (i.e. if you have scrollbars) (10 marks).
Multiple document support: add tab support, so that the user can have multiple drawings open simultaneously! You would need to add File-New Tab and File-Close Tab functionality, and handle loading and saving from each tab as well (10 marks).
Cut-copy-paste: allow users to cut/copy/paste shapes within your application (e.g. to create a copy of a shape). This includes adding standard Edit-Cut, Edit-Copy, Edit-Paste menu items. If a shape is copy-pasted, the new copy should be fully interactive, just as if it had been drawn. You do NOT need to support pasting from an external application (10 marks).
Technical Requirements
The following is a list of technical constraints and guidelines:

The assignment must be programmed using only Kotlin and Java FX (using the versions from the course website).
You should use Gradle. Your application should build using gradle build and execute with gradle run.
You should use the MVC pattern, which at least one clearly defined model and at least two views.
The starting application window should be no larger than 1600x1200. The window should be resizable, as described above.
Appropriate widgets should be selected and used for the interface. Options should appropriately enabled/disabled in the UI (i.e. if an option is unavailable, it should be disabled).
You may use third-party graphics/images, provided that (a) they are licensed to allow you to use them in this way (e.g. Creative Commons), and (b) you comment their origin and license in your README file.
You may use any sample code provided in-class (including on the class repository). If you do this, you must provide a comment in the code indicating the source of the code. If you use and fail to disclose this information, it could be considered an academic violation. You may not use any other third-party code unless you obtain instructor consent ahead of time.
