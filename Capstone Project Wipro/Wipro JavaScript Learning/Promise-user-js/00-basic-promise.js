//Define Promise
const promise = new Promise((resolve, reject)  =>{
    const num = Math.random();
    console.log(" Number is :" + num);
    if (num >= 0.5) {
      resolve("Promise is fulfilled!");
    } else {
      reject("Promise failed!");
    }
  });
  
  // Write a function to handle what o do if promise is resolved
  function handleResolve(value) {
    console.log(value);
  }
  
  // Write a function to handle what o do if promise is rejected
  function handleReject(reason) {
    console.error(reason);
  }
  
  //Call Promise
  promise.then(handleResolve, handleReject);
 
