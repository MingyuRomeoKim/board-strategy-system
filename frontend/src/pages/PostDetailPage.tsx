import React, { useEffect, useState } from 'react';
import { useParams, useNavigate } from 'react-router-dom';
import {
    AppBar,
    Toolbar,
    Typography,
    CircularProgress,
    Container,
    Button,
    Box,
} from '@mui/material';
import axiosInstance from '../api/axiosInstance';
import { Post } from '../types/post';
import dayjs from 'dayjs';

const PostDetailPage: React.FC = () => {
    const { postId } = useParams<{ postId: string }>();
    const navigate = useNavigate();
    const [post, setPost] = useState<Post | null>(null);
    const [loading, setLoading] = useState(true);

    useEffect(() => {
        const fetchPost = async () => {
            try {
                const res = await axiosInstance.get<Post>(`/posts/${postId}`);
                setPost(res.data);
            } catch (e: any) {
                if (e.response?.status === 404) {
                    navigate('/404', { replace: true });
                } else {
                    console.error('게시글 조회 실패', e);
                }
            } finally {
                setLoading(false);
            }
        };

        fetchPost();
    }, [postId, navigate]);

    if (loading) {
        return (
            <>
                <AppBar position="sticky" color="primary">
                    <Toolbar>
                        <Typography variant="h6" component="div">
                            게시글 상세보기
                        </Typography>
                    </Toolbar>
                </AppBar>
                <Container sx={{ mt: 4, textAlign: 'center' }}>
                    <CircularProgress />
                </Container>
            </>
        );
    }

    if (!post) {
        return (
            <>
                <AppBar position="sticky" color="primary">
                    <Toolbar>
                        <Typography variant="h6" component="div">
                            게시글 상세보기
                        </Typography>
                    </Toolbar>
                </AppBar>
                <Container sx={{ mt: 4 }}>
                    <Typography variant="h6">게시글을 찾을 수 없습니다.</Typography>
                </Container>
            </>
        );
    }

    return (
        <>
            <AppBar position="sticky" color="primary">
                <Toolbar>
                    <Typography variant="h6" component="div">
                        게시글 상세보기
                    </Typography>
                </Toolbar>
            </AppBar>

            <Container sx={{ mt: 4 }}>
                <Typography variant="h4" gutterBottom>
                    {post.title}
                </Typography>
                <Typography variant="body2" color="text.secondary" gutterBottom>
                    작성자: {post.author} | 작성일: {dayjs(post.createdAt).format('YYYY-MM-DD HH:mm')}
                </Typography>
                <Typography variant="body1" sx={{ mt: 2, whiteSpace: 'pre-line' }}>
                    {post.content}
                </Typography>

                <Box mt={4}>
                    <Button variant="contained" color="primary" onClick={() => navigate('/')}>
                        목록으로 돌아가기
                    </Button>
                </Box>
            </Container>
        </>
    );
};

export default PostDetailPage;
