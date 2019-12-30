/*
 * 1. let, const = block scope를 가짐
 */
let a = 1
{
    let a = 2
}
console.log('a = %s', a)

const aa = 1
// aa = 2 // Error!

/*
 * 2. class
 */ 
// ES2015 이전
// function Person(name, address) {
//     this.name = name
//     this.address = address
// }

class SuperPerson {
    constructor () {
        this.country = 'korea'
    }
}

class Person extends SuperPerson {
    constructor (name, address) {
        super()
        this.name = name
        this.address = address
        console.log('constructor')
    }
}

const person = new Person('leaf')
console.log(person)

/*
 * 3. string literal
 */
const name = 'leaf'
const str = `name = ${person.name}, address = ${person.address ? person.address : 'unknown'}`
console.log(str)

/*
 * 4. spread operator
 */
function myFunction(x, y, z) {
    console.log(`x = ${x}, y = ${y}, z = ${z}`)
}
const arr = [1, 2, 3]
const arr2 = [4, 5, 6]
myFunction(...arr)

const newArr = [...arr, ...arr2]
console.log(newArr)

const obj = { name: 'leaf', info: { job: 'developer' }, children: [{ name: 'leaf2' }] }
const obj2 = { ...obj } // clone

/*
 * 5. Deconstructuring
 */
const { myName, myAddress } = { myName: 'leaf', myAddress: 'paju' }
console.log(myName, myAddress)

/*
 * 6. Promise & async/await
 */
// function asyncTask (data, callback) {
//     setTimeout(function () {
//         callback(data)
//     }, 2000)
// }
// 
// asyncTask('A', function (data) {
//     console.log(data)
//     asyncTask(data + 'B', function (data2) {
//         console.log(data2)
//         asyncTask(data2 + 'C', function (data3) {
//             console.log(data3)
//         })
//     })
// })

const myTask = function (data) {
    return new Promise(function(resolve, reject) {
        setTimeout(function() {
            if (data) {
                resolve(data) // callback()
            } else {
                reject('error!!')
            }
        }, 2000)
    })
}

// myTask('A')
//     .then(function(res) { // function callback() { ... }
//         console.log(res)
//         return myTask(res + 'B')
//     })
//     .then(function(res2) {
//         console.log(res2)
//         return myTask()
//     })
//     .then(function(res3) {
//         console.log(res3)
//     })

// ES2017 async/await
async function task () {
    try {
        const res = await myTask('A')
        console.log(res)
    } catch (err) {
        return false
    }

    try {
        const res2 = await myTask(res + 'B')
        console.log(res2)

        const res3 = await myTask(res2 + 'C')
        console.log(res3)
    } catch (err) {
        
    }
}
task()

