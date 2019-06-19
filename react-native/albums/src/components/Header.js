import React from 'react';
import { Text, View } from 'react-native';

const Header = (props) => {
  const { textStyle, viewStyle } = styles;

  return (
    <View style={viewStyle}>
      <Text style={textStyle}>{props.headerText}</Text>
    </View>
    
  );
};

const styles = {
  viewStyle: {
    backgroundColor: '#F8F8F8',
    justifyContent: 'center',
    alignItems: 'center',
    height: 70,
    paddingTop: 5,
    elevation: 2,
    position: 'relative'
  },
  textStyle: {
    fontSize: 25,
    fontWeight: 'bold'
  }  
};

// make component available to other areas of the app
export default Header;
