let success = false;
 
function getUsers() {
  return new Promise((resolve, reject) => { // Create a Promise
    setTimeout(() => {
      if (success) {
        resolve([
          { username: 'john', email: 'jennifer@test.com' },
          { username: 'jane', email: 'Ginnelia@test.com' },
        ]);
      } else {
        reject('Failed to the user list');
      }
    }, 1000);
  });
}
 
function onFulfilled(users) {
  console.log(users);
}
function onRejected(error) {
  console.log(error);
}
 
const promise = getUsers();
promise.then(onFulfilled, onRejected); // Map the FulfilHandler and Rejction handler