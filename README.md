# App-to-Web-Application-Firebase

- This projects helps the customer and service provider to interact with one another.
- Customer can upload their data and picture of the product from the mobile app.
- The service provider will get the complete list of customer's requests on the web app.
- The service provider will be able to add comments or reply with the quote or any comments from the web app.
- Customer gets the quote or reply on the mobile app.


## Required :

1. Firebase cloud database linked to the site.
2. Firebase Storage linked to the site.
3. Firebase cloud database linked to the mobile app.
4. Firebase Storage linked to the mobile app.
5. Firebase should have public access to read and write data to firebase.

## Firebase Functionalities used :

1. firebase Authentication.
2. Firestore Database.
3. Firebase Storage.


## Data structure in firebase cloud:

Data collection : gobie

Data Document :  (string) ("UID"+ random) 

Data before Comment:

UID5563 :  {

id: "UID5563" 
name: "Gobie" (string)
note: "I want quotes to paint my honda civic all Black" (string)
      }
      
Data after Comment:      
      
UID5563 :  {

  id: "UID5563" 
  name: "Gobie" (string)
  note: "I want quotes to paint my honda civic all Black" (string)
  Comment: "It will cost you $230.00 +GST " (string)
  CommentType : "true" (string)
  
  }
      
      
## Image storage :

images/imagename

ie. 'images/'+ Imagename

Eg. 
  'images/'+ UID5563
  
