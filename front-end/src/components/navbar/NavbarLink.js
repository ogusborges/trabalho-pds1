import React from 'react';
import PropTypes from 'prop-types';
import './NavbarLink.css'
import { Link } from 'react-router-dom';

const NavbarLink = ({texto, url, current, active}) => {
    
    return <li className={`navbarLink ${current === active ? 'navbarLinkSelected' : ''}`} title={texto}><Link to={url}>{texto}</Link></li>
};

NavbarLink.defaultProps = {
    texto: 'Não definido',
    selected: false
};

export default NavbarLink;