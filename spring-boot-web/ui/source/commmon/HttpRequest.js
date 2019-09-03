import axios from 'axios';

export const JsonAxiosRequest = axios.create({
    //baseURL: 'https://localhost/api/',
    //timeout: 10000,
    headers: {
        Accept: 'application/json',
        'Content-Type': 'application/json;charset=UTF-8',
    },
});

export const DefaultAxiosRequest = axios.create({
    timeout: 10000,
});