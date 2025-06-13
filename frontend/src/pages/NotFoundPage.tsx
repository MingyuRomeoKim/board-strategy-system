// src/pages/NotFoundPage.tsx
import React from 'react';
import { Container, Typography, Button } from '@mui/material';
import { useNavigate } from 'react-router-dom';

const NotFoundPage: React.FC = () => {
    const navigate = useNavigate();

    return (
        <Container sx={{ mt: 8, textAlign: 'center' }}>
            <Typography variant="h3" gutterBottom>
                404
            </Typography>
            <Typography variant="h5" gutterBottom>
                페이지를 찾을 수 없습니다.
            </Typography>
            <Button variant="contained" onClick={() => navigate('/')}>
                홈으로 이동
            </Button>
        </Container>
    );
};

export default NotFoundPage;
