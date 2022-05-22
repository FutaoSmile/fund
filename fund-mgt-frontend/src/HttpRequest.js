import axios from 'axios'
import {notification} from 'ant-design-vue';


//请求超时时长
axios.defaults.timeout = 20000;
axios.defaults.baseURL = process.env.VUE_APP_BASE_DOMAIN + ''//配置接口地址

//POST传参序列化(添加请求拦截器)
axios.interceptors.request.use((config) => {
    //在发送请求之前做某件事
    return config;
}, (error) => {
    return Promise.reject(new Error(error.message));
});

//返回状态判断(添加响应拦截器)
axios.interceptors.response.use((res) => {
    if (res.status !== 200) {
        notification['error']({
            message: '网络错误',
            description: '系统繁忙，请稍后再试',
            duration: 3,
            style: {
                'background-color': '#fff1f0',
                'border': '1px solid #ffa39e'
            }
        });
    }
    //对响应数据做些事
    if (res.data.code !== 0) {
        notification['error']({
            message: '错误',
            description: res.data.message,
            duration: 3,
            style: {
                'background-color': '#fff1f0',
                'border': '1px solid #ffa39e'
            }
        });
    }
    return res.data.data;
}, (error) => {
    notification['error']({
        message: error.message,
        description: '系统繁忙，请稍后再试',
        duration: 3,
        style: {
            'background-color': '#fff1f0',
            'border': '1px solid #ffa39e'
        }
    });
    return Promise.reject(error);
});

//返回一个Promise(发送post请求)
export function httpPost(url, body) {
    return new Promise((resolve, reject) => {
        axios.post(url, body, {
            headers: {
                'Content-Type': 'application/json'
            }
        })
            .then(response => {
                resolve(response);
            }, err => {
                reject(err);
            })
            .catch((error) => {
                reject(error)
            })
    })
}

//返回一个Promise(发送get请求)
export function httpGet(url, param) {
    return new Promise((resolve, reject) => {
        axios.get(url, {params: param})
            .then(response => {
                resolve(response)
            }, err => {
                reject(err)
            })
            .catch((error) => {
                reject(error)
            })
    })
}

export default {
    fetchPost: httpPost,
    fetchGet: httpGet,
}
