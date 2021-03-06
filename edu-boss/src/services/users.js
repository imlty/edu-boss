/**
 * Users service
 */

//
import { baseRequest, postRequest, bossBase } from "./common";

export const getUserInfo = async courseId => {
  return await baseRequest(`${process.env.VUE_APP_API_FAKE}/user/getInfo`);
};

export const getUserList = async params => {
  return await postRequest(`${bossBase}/user/getUserPages`, params);
};

//通过路由守卫获取用户权限
export const getUserPermissions = async () => {
  return await baseRequest(
    `${process.env.VUE_APP_API_FAKE}/user/getUserPermissions`
  );
};

// export const getUserPermissions = async () => {
//   return await baseRequest(`${bossBase}/permission/getUserPermissions`)
// }

export const forbidUser = async userId => {
  return await postRequest(`${bossBase}/user/forbidUser`, { userId });
};
