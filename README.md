# Image.(in): A Basic Image Processing Application

## *What Does Image.(in) Do?*

Taking pictures is cool, but sometimes reality
just isn't as interesting as one would have hoped...
that's where Image.(in) comes in! Image.(in) is intended to
be a basic image processing application that allows
users manipulate a locally stored image by adding different
color filters/editors.

## *Who Will Use It?*

- **recreational photographers** who want to warp reality with
  their pictures
- **social media users** looking for a cool way to
  spice up their photos
- **coding enthusiasts** who want to learn more about
  image manipulation
- **photo/video editors** looking to intrigue their
  clients with new visual art styles

## *Why Image.(in) Interests Me:*

From self-driving cars to mobile applications,
computer software has many uses. However,
its artistic applications can easily go less appreciated.
I recently became interested in video editing
and, in the process, have discovered wonderful applications
such as Apple's Final Cut Pro, which combine software with
human creativity to manipulate videos and images
in exciting ways! Exploring a basic image processing
application will allow me to gain a better understanding of
software development in the context of an industry that I have
a personal interest in!

<img width="712" alt="Screen Shot 2023-01-16 at 5 21 28 PM" src="https://user-images.githubusercontent.com/49254129/212790234-fc5899fa-4f3f-47a2-a21f-c454f6a76302.png">
<img width="712" alt="Screen Shot 2023-01-16 at 5 24 01 PM" src="https://user-images.githubusercontent.com/49254129/212790444-01e5db2e-7a6a-4b46-84f8-83eaff84dab8.png">
<img width="712" alt="Screen Shot 2023-01-16 at 5 21 45 PM" src="https://user-images.githubusercontent.com/49254129/212790471-cb852cfd-d622-45ab-b928-f4b0f399901b.png">
<img width="712" alt="Screen Shot 2023-01-16 at 5 21 58 PM" src="https://user-images.githubusercontent.com/49254129/212790483-0245fdf4-781f-4923-b08e-583000c00b18.png">
<img width="712" alt="Screen Shot 2023-01-16 at 5 22 09 PM" src="https://user-images.githubusercontent.com/49254129/212790490-7e798a1a-82de-48db-9306-b339feae9c64.png">
<img width="712" alt="Screen Shot 2023-01-16 at 5 22 34 PM" src="https://user-images.githubusercontent.com/49254129/212790501-68809933-6814-4598-bc2d-37425bf65126.png">
<img width="712" alt="Screen Shot 2023-01-16 at 5 23 03 PM" src="https://user-images.githubusercontent.com/49254129/212790508-ad2c77cd-f15b-4b3b-a3df-dc7a5b720ed8.png">
<img width="712" alt="Screen Shot 2023-01-16 at 5 23 29 PM" src="https://user-images.githubusercontent.com/49254129/212790513-2a086570-a4a3-4fab-8532-078d80d33953.png">

## *User Stories:*

- As a user, I want to be able to ***choose the name of my project*** (which will dictate the file name to which
  the project is eventually saved)
- As a user, I want to be able to ***randomize the pixel colors*** of my image after initializing
  its dimensions
- As a user, I want to be able to ***add a filter*** to my image
- As a user, I want to be able to ***remove (undo) a filter*** from my image
- As a user, I want to be able to ***remove all (undo all) filters*** from my image
- As a user, I want to be able to ***remove all of one type of filter*** from my image
- As a user, I want to be able to ***view a history of what filters have been applied*** to my image
- As a user, I want to be able to ***quit the application and save*** my edits current progress
  to file before final processing
- As a user, I want to be able to ***save my edits progress while still editing*** so that I can revert to
  that point in my progress if I decide to not want the progress I made after saving
- As a user, I want to be able to ***revert to the last saved version*** of my edit while still
  editing
- As a user, I want to be given the option to ***load a previous work-in-progress project***
  upon opening the application
- As a user, I want to be able to ***abandon a project*** to remove it from being accessed again
- As a user, I want to be able to ***save my project to a gallery*** after processing the final product
- As a user, I want to be able to ***view my finished projects in a gallery***

## *Instructions for Grader*

- You can generate the first required event related to adding Xs to a Y by clicking
the ***new project*** button and filling out the required fields (name must match "image4U"
where the number can be any digit and the capital letter must be from the English
alphabet), then you can ***add a filter*** by clicking the add filter button and choosing
the desired filter. Changes can be viewed in the history panel in the right of the tool menu panel.
- You can generate the second required event related to adding Xs to a Y by clicking
either the ***undo*** or ***undo all*** buttons which will update which filters are added the image.
Changes can be viewed in the history panel in the right of the tool menu
- You can locate my visual component by either looking at the top of each panel
at the indicative logo/icon or by pressing ***process project*** to see the final
product of your edit (best view if you choose yes to "randomize color?" before
processing).
- You can save the state of my application by clicking the ***save progress*** button in the
tool menu panel.
- You can reload the state of my application by choosing the desired project
from the dropdown ***\<options>*** and clicking the load button to the right.

## *Phase 4: Task 2*
image created with height (128) and width (128) \
mirror filter has been added to current Image\
previous filter has been removed\
negative filter has been added to current Image\
mirror filter has been added to current Image\
edit history has been cleared\
colorGradient filter has been added to current Image\
previous filter has been removed\
colorGradient filter has been added to current Image\
pixelate filter has been added to current Image\
image has been processed

## *Phase 4: Task 3*
If I had more time, I would refactor the design of my GUI. 
Currently, there are too many unnecessary places where fields are
passed down from class-to-class which leads to a UML mess and
code that is not easily updatable. Here's what I would do to 
refactor:
- Use the Singleton pattern to ensure that ImageAppGUI is instantiated once
and is globally accessible (this would reduce the excessive presence of 
ImageAppGUI field types along the chain of GUI panels)
- I would remove all the Image field types from GUI panels
along the chain from ImageAppGUI because the panels could access
the image from the single instance of ImageAppGUI that is globally accessible
  (this would reduce the excessive presence of Image field types along the chain
of GUI panels)
