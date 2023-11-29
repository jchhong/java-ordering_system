# Simple Ordering System for Restaurants

## 1. Introduction

This desktop application is for restaurant waiters /
waitresses to manage meal orders. Users can edit and view
the menu, add dishes to cart, place an order, edit an order,
and check out. This application is faced to restaurant 
waiters / waitresses.

I am interested in this project because an automatic
food ordering system is a lot more convenient than the 
traditional pen and paper way of ordering. It offers 
a way to easily track and manage orders. I hope this simple 
application can help restaurants in my hometown.

## 2. User Stories
* As a user, I want to be able to add / edit the menu.
* As a user, I want to be able to view the menu.
* As a user, I want to be able to add / remove dishes
to the order.
* As a user, I want to be able to calculate the subtotal,
subtotal after discount, and total price.
* As a user, I want to be able to view the order.
* As a user, I want to be able to save my menu (if I so choose)
* As a user, I want to be able to be able to load my menu from 
file (if I so choose)

## 3. Reference
* Class `JsonReader`,`JsonReaderTest`, `JsonWriter`,`JsonWriterTest`
partly cited from: ubc CPSC210 2023W1 JsonSerializationDemo

## 4. Instructions for Grader
* You can add a dish to my menu by first entering the new dish name and the new dish 
price in the text fields, then clicking the "Add Dish to Menu" button.
* You can remove a dish by first selecting the row of the dish, then clicking 
the "Remove Dish from Menu" button.
* The background image is provided, which is a cartoon cooking image.
* You can save the menu by clicking the "Save My Menu" button.
* You can reload the menu by clicking the "Reload My Menu" button.

## 5. Phase 4: Task 2
Examples of events:
- Added a new dish: Strawberry $2.45
- Removed dish: Salmon Salad $9.85

## 6.Phase 4: Task 3 Reflection
If I had more time, I would add an association from OrderingAppGUI to
Order. I did not realize the part for Ordering function for my GUI since I don't have
that much time. Another change I'm thinking of is considering an observering
pattern for Menu, that is, notify Order when there's a new dish added
to Menu. But my project is now simple with not many functions, this may 
not be needed.