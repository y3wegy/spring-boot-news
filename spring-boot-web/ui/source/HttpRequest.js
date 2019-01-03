import axios from 'axios';

export const jsonInstance = axios.create({
    //baseURL: 'https://localhost/api/',
    //timeout: 10000,
    headers: {
        Accept: "application/json",
        "Content-Type": "application/json;charset=UTF-8"
    }
});

export const defaultInstance = axios.create({
    timeout: 10000
});