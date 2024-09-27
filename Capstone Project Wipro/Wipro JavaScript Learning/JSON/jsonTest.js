//1. Define and access the object
let myOrder, i;
  
// JSON Object is created with name myOrder
myOrder = {
    "name_of_the_product": "Laptop",
    "cost": "1300$",
    "warranty": "1 year "
};
  
// Accessing for particular detail
// from object myOrder
i = myOrder.name_of_the_product;  
// It prints the detail of name
// of the product
console.log(i);
 
//2.  Nested JSON Object
myOrder = {
    "name of product" : "Mobile",
    "cost" : "799",
    "warranty" : {
                "warranty1" : "6 months",
                "warranty2" : "10 months",
                }
};
i = myOrder.warranty.warranty2; // .dot notation
console.log(i);
 
//data using loop
for (item in myOrder) {
    // Accessing object in looping
    // using bracket notation
    console.log(myOrder[item]);
}