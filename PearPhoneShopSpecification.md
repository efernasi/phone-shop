# phone-shop
“Pear Phone Shop” sells the following product Entities:
- Pear myPhoneX (500.00 €);
- Pear myPhone7 (300.00 €);
- SIM Card (20 €);
- Phone Insurance (120 € for 2 years);
- Phone Case (10 €)

##### Following the list of rules below:
- One SIM Card is added for free for each myPhone sold;
- SIM cards sold on their own (not with the phone) are “Buy One Get One Free”;
- Insurance is discounted 25% if you buy any type of phone;
- There is a “4 for 3” offer on Phone Cases
- the law prevents anyone buying more than 10 SIM cards in a single purchase
- a 14% VAT is added to all purchases, BUT insurance productEntities are exempt;
     
     
##### Your task is to:
- write a very simple API in Spring Boot so that:
  - Return existing list of productEntities (No need for product search, just get the full
list);
  - Details for existing productEntities sold in store can be updated;
  - Accepts in input a list of productEntities that the shopper has in the orderRequest (e.g.
Phone Insurance, Pear MyPhoneX etc…) and returns and output similar to
the receipt of a supermarket.
  - Uses YAML for the API definition;
 Send back the working Eclipse project;


#### Important
- Only Spring Boot and Junit/Mockito can be used;
- No Spring EL allowed to calculate the rules;
- Please use the in-memory database H2 as we don’t need additional complexity;
- The API is very simple, so:
  - No login is required;
  - No Security of any kind is required;
  - No need to distinguish types of users for this assignment, let’s assume that
anybody can add, modify or buy....
  - No Front End is required, just a simple feed is enough;
  - No API to return/Add or Modify the rules is required
  - No actual orderRequest function is required, the product list should be sent in a
single request;
  - No product search is required
- The recommended approach is TDD that along with Clean Code practice is a MUST to be successful in this assignment;
 
- UT Vs Integration tests, which one are you going to use? Why?
- Please use YAML for the API definitions;
- Last but not least, if a requirement is ambiguous please make a reasonable
assumption;