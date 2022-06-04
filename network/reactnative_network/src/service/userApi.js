import React from 'react';

const BASE_URL = "https://reqres.in/api/"
const PAGINATION_PAGE_SIZE = 6

export const getUsers = async (page) => {
  try {
    const response = await fetch(`${BASE_URL}users/?page=${page}`);
    const json = await response.json();
    return {
      data: json.data,
      message: null
    }
  } catch (e) {
    return {
      data: null,
      message: e
    }
  }
}

export const getUserById = async (id) => {
  try {
    const response = await fetch(`${BASE_URL}users/${id}`);
    const json = await response.json();
    return {
      data: json.data,
      message: null
    }
  } catch (e) {
    return {
      data: null,
      message: e
    }
  }
}

export const createUser = async (user) => {
  try {
    const response = await fetch(`${BASE_URL}users`, {
      method: 'POST',
      body: JSON.stringify({
        name: user.firstName,
        job: user.lastName
      })
    });
    const json = await response.json();
    return {
      data: {
        id: json.id,
        firstName: user.firstName,
        lastName: user.lastName,
        email: user.email,
        avatar: user.avatar,
      },
      message: null
    }
  } catch (e) {
    return {
      data: null,
      message: e
    }
  }
}

export const updateUser = async (user) => {
  try {
    const response = await fetch(`${BASE_URL}users/${user.id}`, {
      method: 'PUT',
      body: JSON.stringify({
        name: user.firstName,
        job: user.lastName
      })
    });
    const json = await response.json();
    return {
      data: json,
      message: null
    }
  } catch (e) {
    return {
      data: null,
      message: e
    }
  }
}

export const deleteUser = async (id) => {
  try {
    await fetch(`${BASE_URL}users/${id}`, {
      method: 'DELETE',
    });
    return {
      data: true,
      message: null
    }
  } catch (e) {
    return {
      data: null,
      message: e
    }
  }
}
