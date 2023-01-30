import React from 'react';
import NavbarLink from './NavbarLink.js';
import './Navbar.css';

const Navbar = ({active}) => {
    const links = [
        {
            texto: "Início",
            url: "/home"
        },
        {
            texto: "Relatório",
            url: "/relatorios"
        },
        {
            texto: "Formulário",
            url: "/formulario"
        }
    ];

    return (
        <header>
            <div id="logotipoContainer">
                <img src="/ufulogo.svg" alt="Logotipo da UFU"/>
            </div>
            <div id="menuLinkContainer">
                <ul>
                    {links.map(
                        (link, index) => <NavbarLink current={index} key={index} texto={link.texto} url={link.url} active={active}/>
                    )}

                    {/* <NavbarLink texto="Início" url = "/" active={active}/>
                    <NavbarLink texto="Relatório" url = "/relatorios" active={active} />
                    <NavbarLink texto="Formulário" url= "/formulario" active={active} /> */}
                </ul>
            </div>
        </header>
    );
};

export default Navbar;