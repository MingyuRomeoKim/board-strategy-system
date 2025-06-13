import React, { useState } from 'react';
import { Container, Button, CircularProgress, Box } from '@mui/material';
import StrategyToggle from '../components/StrategyToggle';
import PostCard from '../components/PostCard';
import { useLoadStrategy } from '../strategy/useLoadStrategy';

const PostListPage: React.FC = () => {
    const [strategy, setStrategy] = useState<'paging' | 'infinite'>('paging');
    const { posts, loadMore, targetRef, isEnd, loading } = useLoadStrategy(strategy);

    return (
        <>
            <StrategyToggle strategy={strategy} onChange={setStrategy} />

            <Container maxWidth="sm" sx={{ paddingTop: 2 , minHeight: '100vh' }}>
                {posts.map((post) => (
                    <PostCard key={post.id} post={post} />
                ))}

                {/* 페이징 방식일 경우: 더보기 버튼 */}
                {strategy === 'paging' && !isEnd && (
                    <Box display="flex" justifyContent="center" my={2}>
                        <Button variant="outlined" onClick={loadMore} disabled={loading}>
                            {loading ? '로딩 중...' : '더보기'}
                        </Button>
                    </Box>
                )}

                {strategy === 'infinite' && (
                    <>
                        <div
                            ref={targetRef}
                            style={{
                                height: 40,
                                marginTop: 16,
                            }}
                        >
                        </div>
                    </>
                )}

                {/* 로딩 스피너 공통 */}
                {loading && (
                    <Box display="flex" justifyContent="center" my={2}>
                        <CircularProgress size={24} />
                    </Box>
                )}
            </Container>
        </>
    );
};

export default PostListPage;
