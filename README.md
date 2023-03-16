# CS492 Final Project

## Description:

This app will be a health and fitness app. Users will be able to enter in data such as fitness and
health activity and food items eaten, then store that information. Fitness data such as walking will
be calculated using the phone’s sensors. The FoodData Central API is the API we intend to incorporate
into this app.

## API:
The FoodData Central API will be used for users to search for food and get nutrition information.
That information will be stored for the user and can be retrieved later. We will primarily use the
/foods/search endpoint for users to look up foods.  We may use a user selection made there to access
the Food Details endpoint with the {fdcId} query string being obtained from the Food Search endpoint.

## UI:

The UI will have two primary screens which the user can navigate to via a navigation bar on the top
or bottom of the screen. The main screens will show a list of each day’s activity and nutrition
information respectively. When clicked on the view, it will have a new activity launch where the data
will be displayed. Each of these screens will have a sidebar, or an options menu, to navigate to
different activities such as retrieving fitness data or nutritional information. Using intents, the
user can open a map of the location of their workout, or share data about their workout and nutrition.

## Features:

One of the app’s features is the usage of the device’s sensors, specifically the accelerometer. We
will use this for calculating a user's step count.

Another feature that will be implemented is animation. Animation can be used when the user submits
data, a picture can be attached to the appropriate data submitted, such as food or an activity. These
generic pictures can be animated to the users screen. Another option might be to animate a graph of
the time of a user's fitness activity.

## Team Members:
  * Yevgeniy Chaplygin
  * Charlemagne P Martinez
  * Angelo Dechayut Matteo
  * Katie Strauss
  * Spencer Miller