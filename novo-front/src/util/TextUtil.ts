export default function getRndInteger(): Number {
    let min = Number.MIN_VALUE
    let max = Number.MAX_VALUE

    return Math.floor(Math.random() * (max - min) ) + min;
}