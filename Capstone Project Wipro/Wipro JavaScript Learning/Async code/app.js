async function displayname()
{
    console.log('Inside the displayName method');
    const response = await fetch('https://api.github.com/users');
    console.log('Before response');
    const users= await response.json();
    return users;
    //return "Japan"
}
console.log('Before calling display' );
let name  = displayname();
console.log(" I am waiting");
name.then(data=> console.log(data))
console.log("Last line of this js file");