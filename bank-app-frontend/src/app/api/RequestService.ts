import Axios from "axios";
import {notification } from 'antd';

enum Method {
    GET = 'get',
    PUT = 'put',
    POST = 'post',
    DELETE = 'delete'
}

const sendRequest = (method: Method, url: string, data: any) => {
    return  new Promise<any>((resolve, reject) => {
        Axios.request({method, url, data}).then(data=>resolve(data)).catch((error)=>{
            notification.error({
                message: 'Request error',
                placement:'bottomRight'
            });
            return reject(error);
        });
    })
};

export const RequestService = {
    get: (endpoint: string, parameters?: any, data?: any) => sendRequest(Method.GET, endpoint, data),
    put: (endpoint: string, parameters?: any, data?: any) => sendRequest(Method.PUT, endpoint, data),
    post: (endpoint: string, parameters?: any, data?: any) => sendRequest(Method.POST, endpoint, data),
    delete: (endpoint: string, parameters?: any, data?: any) => sendRequest(Method.DELETE, endpoint, data)
};