import React from "react";
import "./UserInfo.css";

const UserInfo = ({nome, telefones}) => {
    return (
        <div id="userInfoContainer">
            <img src="/avatar.jpg" />
            <h1>{nome}</h1>
            <p>Uma breve descrição sobre a minha pessoa.</p>
            {telefones.map(
                (telefone, index) => <p className="userContactInfo" key={index}>{telefone}</p>
            )}

        </div>
    )
}

export default UserInfo;