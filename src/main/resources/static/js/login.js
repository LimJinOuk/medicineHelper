async function login()
{
    const email = document.getElementById('email').value;
    const password = document.getElementById('password').value;

    const jsonData = {
        email: email,
        password: password
    };

    await fetch('/DoLogin', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(jsonData)
    })
        .then(response => {
            if(response.ok)
            {
                const accessToken = response.headers.get('Authorization')

                const tokenOnly = accessToken.replace('Bearer ', '');
                localStorage.setItem('jwtToken', tokenOnly);
            }
            return response.json();
        })
        .then(data=>
        {
            console.log("data"+data)
            if(data.Login_Result === "Success")
            {
                alert("로그인 성공")
                window.location.href = "/"
            }
            else
            {
                alert(data.Err)
            }

        })

}