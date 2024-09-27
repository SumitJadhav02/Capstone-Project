// Case 1: Boolean to String Conversion
let value = true;
console.log('The type of boolean variable is: ' + typeof value); // boolean

// Convert boolean to string
value = String(value); // now value is a string "true"
console.log('The type of value after string conversion: ' + typeof value); // string

// Case 2: Numeric Conversion

// a. Strings are automatically converted to numbers in mathematical operations
console.log("6" / "2"); // 3, strings are converted to numbers

// b. Convert string to number
let str = "123";
console.log(typeof str); // string

let num = Number(str); // becomes the number 123
console.log(typeof num); // number

// c. If the string is not a valid number, the result of such conversion is NaN
let age = Number("an arbitrary string instead of a number");
console.log(age); // NaN, conversion failed

// d. More numeric conversions
console.log(Number(" 123 ")); // 123, extra spaces are ignored
console.log(Number("123z")); // NaN, error reading a number at "z"
console.log(Number(true)); // 1, boolean true becomes 1
console.log(Number(false)); // 0, boolean false becomes 0

// Case 3: Boolean Conversion

// a. Convert different values to boolean
console.log(Boolean(1)); // true, 1 is truthy
console.log(Boolean(0)); // false, 0 is falsy
console.log(Boolean("hello")); // true, non-empty string is truthy
console.log(Boolean("")); // false, empty string is falsy
console.log(Boolean("0")); // true, non-empty string "0" is truthy
console.log(Boolean(" ")); // true, space (non-empty string) is truthy
