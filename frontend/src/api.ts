import axios from "axios";
import { FormikValues } from "formik";

const API_URL = process.env.REACT_APP_API_URL;

export function axiosGet(props:string){
    return axios(API_URL + props )
}

export function axiosPost(props:string, Values: FormikValues){
    return axios.post(API_URL + props,Values)
}

export function axiosPut(props:string,values: FormikValues){
    return axios.put(API_URL + props,values)
}
export function axiosDelete(props:string){
    return axios.delete(API_URL + props)
}