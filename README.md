# StatusViewer Library

A Jetpack Compose library for displaying a series of images with a configurable progress indicator. The indicator can be placed either at the top or bottom of the images.
This library accept imagesList in the Int format like val list = listOf(R.drawable.image1) etc.

## Features

- Display a list of images in a horizontal pager.
- Automatically animate the progress of each image.
- Configurable progress indicator placement (top or bottom).
- Clickable images with customizable click actions.

## Demo

https://github.com/user-attachments/assets/12234a79-d3da-4978-9d69-bfab17ca97de



## Installation

Add the following in your `settings.gradle` of `app` module: 

```
 repositories {
    maven(url = "https://jitpack.io")
}
```
then add the following to your `build.gradle` file:
```
dependencies {
    implementation 'com.github.daanidev:statusviewer:1.0.2'
}
```

##Usage

```ruby

// By default Indicator Location is on Top

StatusViewer(
 modifier = Modifier
  .fillMaxWidth()
  .height(300.dp),
 imagesList = list,
 progressColor = Color.Red
)

// If you want to show indicator at the bottom of the image then you can use

StatusViewer(
 modifier = Modifier
  .fillMaxWidth()
  .height(300.dp),
 imagesList = list,
 progressColor = Color.Red,
 indicatorLocationType = IndicatorLocationType.BOTTOM
)

// To create Statuses Like WhatsApp, Instagram or any social media app

StatusViewer(
 modifier = Modifier
  .fillMaxSize(),
 imagesList = list,
 progressColor = Color.Red,
 indicatorLocationType = IndicatorLocationType.BOTTOM
)

```

## Getting Callback

```ruby
```ruby
StatusViewer(
 modifier = Modifier
  .fillMaxWidth()
  .height(300.dp),
 imagesList = list,
 progressColor = Color.Red
){
  // handle on click here
}

```

## Developed By

<table>
  <tr>
    <td>
      <img src="https://media.licdn.com/dms/image/C5603AQFQBXt5ODHhig/profile-displayphoto-shrink_800_800/0/1641730136128?e=1728518400&v=beta&t=J-BxmCnxkva4TgvuKaKbMxYkueOr7N8De31kJbTUfZA" alt="Muhammad Danish" width="100" height="100"/>
    </td>
    <td style="padding-left: 10px;">
      <strong>Muhammad Danish</strong><br/><br/>
      <a href="https://daanidev.medium.com/" target="_blank" style="display: inline-block; padding: 8px 16px; margin: 4px 0; font-size: 14px; color: white; background-color: #00ab6c; text-decoration: none; border-radius: 4px;">Medium</a><br/>
      <a href="https://www.linkedin.com/in/mdanish12/" target="_blank" style="display: inline-block; padding: 8px 16px; margin: 4px 0; font-size: 14px; color: white; background-color: #0077b5; text-decoration: none; border-radius: 4px;">LinkedIn</a>
    </td>
  </tr>
</table>

## TODO
- Implement Image Loading from URL
