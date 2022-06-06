const BASE_URL = "https://reqres.in/api/"

export const api_getUserList = async (page) => {
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

export const api_getUserById = async (id) => {
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

export const api_createUser = async (user) => {
  try {
    const response = await fetch(`${BASE_URL}users`, {
      method: 'POST',
      body: JSON.stringify({
        name: user.first_name,
        job: user.last_name
      })
    });
    const json = await response.json();
    return {
      data: {
        id: json.id,
        first_name: user.first_name,
        last_name: user.last_name,
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

export const api_updateUser = async (user) => {
  try {
    const response = await fetch(`${BASE_URL}users/${user.id}`, {
      method: 'PUT',
      body: JSON.stringify({
        name: user.first_name,
        job: user.first_name
      })
    });
    return {
      data: user,
      message: null
    }
  } catch (e) {
    return {
      data: null,
      message: e
    }
  }
}

export const api_deleteUser = async (id) => {
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
